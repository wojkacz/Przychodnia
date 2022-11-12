package pl.kaczmarek.ciezka.bedka.przychodnia;

import java.time.LocalDate;
import java.time.LocalTime;

public class Constants {
    public static final String VISIT_DATES_NOT_FOUND_FOR_DOCTOR         = "Nie znaleziono wolnych terminów dla lekarza o podanym ID!";
    public static final String DOCTOR_NOT_FOUND                         = "Lekarz o podanym ID nie istnieje!";
    public static final String REGISTRATION_NOT_FOUND                   = "Nie znaleziono rejestracji dla podanych danych!";
    public static final String PATIENT_NOT_FOUND                        = "Nie znaleziono pacjenta o podanych danych!";
    public static final String PATIENT_DATA_DOES_NOT_MATCH              = "Dane pacjenta nie zgadzaja sie dla podanego PESELu!";
    public static final String REGISTRATION_PATIENT_DATA_DOES_NOT_MATCH = "Dane pacjenta nie zgadzaja sie z danymi zarejestrowanego pacjenta!";
    public static final String CLINIC_NOT_FOUND                         = "Nie znaleziono kliniki o podanym ID!";
    public static final String INCORRECT_DATE_OR_TIME                   = "Nie znaleziono terminu o podanej dacie i godzinie!";
    public static final String DATE_TIME_OCCUPIED                       = "Wybrany termin jest zajety!";
    public static final String VISIT_CANCELLED                          = "Wizyta zostala anulowana!";
    public static final String VISIT_ALREADY_CANCELLED                  = "Wizyta jest juz anulowana!";

    public static final LocalDate VISIT_DATES_START_DAY     = LocalDate.now().plusDays(1);
    public static final LocalDate VISIT_DATES_FINAL_DAY     = LocalDate.now().plusWeeks(2);
    public static final LocalTime VISIT_TIMES_START_TIME    = LocalTime.of(8, 0);
    public static final LocalTime VISIT_TIMES_FINAL_TIME    = LocalTime.of(16, 1);
    public static final Integer   VISIT_LENGTH_MINUTES      = 30;
}
