package org.realityforge.timeservice;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import javax.annotation.Nonnull;
import javax.annotation.Resource;
import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Typed;
import javax.transaction.TransactionSynchronizationRegistry;
import javax.transaction.Transactional;

@ApplicationScoped
@Transactional( Transactional.TxType.REQUIRED )
@ConcurrencyManagement( ConcurrencyManagementType.BEAN )
@Typed( TimeService.class )
public class TimeServiceImpl
  implements TimeService
{
  private static final String KEY_SUFFIX = ".NOW";
  @Resource
  private TransactionSynchronizationRegistry _registry;

  @Override
  public long currentTimeMillis()
  {
    return now();
  }

  @Nonnull
  @Override
  public Date currentDate()
  {
    return new Date( now() );
  }

  @Nonnull
  @Override
  public LocalDate currentLocalDate()
  {
    return currentLocalDateTime().toLocalDate();
  }

  @Nonnull
  @Override
  public LocalDateTime currentLocalDateTime()
  {
    return LocalDateTime.ofInstant( Instant.ofEpochMilli( now() ), ZoneId.systemDefault() );
  }

  private synchronized long now()
  {
    final String key = TimeService.class.getName() + KEY_SUFFIX;
    final Long now = (Long) _registry.getResource( key );
    if ( null == now )
    {
      final long currentTimeMillis = System.currentTimeMillis();
      _registry.putResource( key, currentTimeMillis );
      return currentTimeMillis;
    }
    else
    {
      return now;
    }
  }
}
