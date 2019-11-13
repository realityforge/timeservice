package org.realityforge.timeservice;

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
  public synchronized long currentTimeMillis()
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
