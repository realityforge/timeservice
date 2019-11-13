package org.realityforge.timeservice;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import javax.annotation.Nonnull;

public interface TimeService
{
  long currentTimeMillis();

  @Nonnull
  default Date currentDate()
  {
    return new Date( currentTimeMillis() );
  }

  @Nonnull
  default LocalDate currentLocalDate()
  {
    return currentLocalDateTime().toLocalDate();
  }

  @Nonnull
  default LocalDateTime currentLocalDateTime()
  {
    return LocalDateTime.ofInstant( Instant.ofEpochMilli( currentTimeMillis() ), ZoneId.systemDefault() );
  }
}
