package org.realityforge.timeservice;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import javax.annotation.Nonnull;

public interface TimeService
{
  long currentTimeMillis();

  @Nonnull
  Date currentDate();

  @Nonnull
  LocalDate currentLocalDate();

  @Nonnull
  LocalDateTime currentLocalDateTime();
}
