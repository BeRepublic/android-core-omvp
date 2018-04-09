package com.omvp.app.injector.module;

import android.text.TextUtils;

import com.omvp.commons.LocalDateUtils;

import org.modelmapper.AbstractConverter;
import org.modelmapper.ModelMapper;
import org.threeten.bp.Instant;
import org.threeten.bp.LocalDate;
import org.threeten.bp.LocalDateTime;
import org.threeten.bp.ZoneId;
import org.threeten.bp.format.DateTimeFormatter;

import java.math.BigDecimal;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public abstract class MapperModule {

    @Provides
    @Singleton
    static ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        addLocalDateConverter(modelMapper);
        addLocalDateTimeConverter(modelMapper);
        addBigDecimalConverter(modelMapper);

        return modelMapper;
    }

    private static void addLocalDateConverter(ModelMapper modelMapper) {
        modelMapper.createTypeMap(Long.class, LocalDate.class);
        modelMapper.addConverter(new AbstractConverter<Long, LocalDate>() {
            @Override
            protected LocalDate convert(Long source) {
                if (source == null) return null;
                return LocalDateUtils.toLocalDate(source * 1000);
            }
        });
        modelMapper.createTypeMap(LocalDate.class, Long.class);
        modelMapper.addConverter(new AbstractConverter<LocalDate, Long>() {
            @Override
            protected Long convert(LocalDate source) {
                if (source == null) return null;
                return LocalDateUtils.toMiliseconds(source) / 1000;
            }
        });
        modelMapper.createTypeMap(LocalDate.class, String.class);
        modelMapper.addConverter(new AbstractConverter<LocalDate, String>() {
            @Override
            protected String convert(LocalDate source) {
                if (source == null) return null;
                return source.format(DateTimeFormatter.ISO_DATE.withZone(ZoneId.systemDefault()));
            }
        });
        modelMapper.createTypeMap(String.class, LocalDate.class);
        modelMapper.addConverter(new AbstractConverter<String, LocalDate>() {
            @Override
            protected LocalDate convert(String source) {
                if (source == null || TextUtils.isEmpty(source)) return null;
                return LocalDate.parse(source, DateTimeFormatter.ISO_DATE.withZone(ZoneId.systemDefault()));
            }
        });
    }

    private static void addLocalDateTimeConverter(ModelMapper modelMapper) {
        modelMapper.createTypeMap(Long.class, LocalDateTime.class);
        modelMapper.addConverter(new AbstractConverter<Long, LocalDateTime>() {
            @Override
            protected LocalDateTime convert(Long source) {
                if (source == null) return null;
                return LocalDateTime.ofInstant(Instant.ofEpochMilli(source * 1000), ZoneId.systemDefault());
            }
        });
        modelMapper.createTypeMap(LocalDateTime.class, Long.class);
        modelMapper.addConverter(new AbstractConverter<LocalDateTime, Long>() {
            @Override
            protected Long convert(LocalDateTime source) {
                if (source == null) return null;
                return source.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli() / 1000;
            }
        });
        modelMapper.createTypeMap(LocalDateTime.class, String.class);
        modelMapper.addConverter(new AbstractConverter<LocalDateTime, String>() {
            @Override
            protected String convert(LocalDateTime source) {
                if (source == null) return null;
                return source.format(DateTimeFormatter.ISO_DATE.withZone(ZoneId.systemDefault()));
            }
        });
        modelMapper.createTypeMap(String.class, LocalDateTime.class);
        modelMapper.addConverter(new AbstractConverter<String, LocalDateTime>() {
            @Override
            protected LocalDateTime convert(String source) {
                if (source == null || TextUtils.isEmpty(source)) return null;
                return LocalDateTime.parse(source, DateTimeFormatter.ISO_DATE.withZone(ZoneId.systemDefault()));
            }
        });
    }

    private static void addBigDecimalConverter(ModelMapper modelMapper) {
        modelMapper.createTypeMap(String.class, BigDecimal.class);
        modelMapper.addConverter(new AbstractConverter<String, BigDecimal>() {
            @Override
            protected BigDecimal convert(String source) {
                return new BigDecimal(source);

                // Not sure we need all this following conversion

//                BigDecimal bigDecimal = null;
//                try {
//                    int value = Integer.parseInt(source);
//                    bigDecimal = BigDecimal.valueOf((float) value / 100);
//                } catch (NumberFormatException e) {
//                    Timber.e(e.getMessage(), e);
//                }
//                return bigDecimal;
            }
        });
        modelMapper.createTypeMap(BigDecimal.class, String.class);
        modelMapper.addConverter(new AbstractConverter<BigDecimal, String>() {
            @Override
            protected String convert(BigDecimal source) {
                return source.toString();
            }
        });

        modelMapper.createTypeMap(Float.class, BigDecimal.class);
        modelMapper.addConverter(new AbstractConverter<Float, BigDecimal>() {
            @Override
            protected BigDecimal convert(Float source) {
                return new BigDecimal(String.valueOf(source));
            }
        });

        modelMapper.createTypeMap(BigDecimal.class, Float.class);
        modelMapper.addConverter(new AbstractConverter<BigDecimal, Float>() {
            @Override
            protected Float convert(BigDecimal source) {
                return source.floatValue();
            }
        });

        modelMapper.createTypeMap(Double.class, BigDecimal.class);
        modelMapper.addConverter(new AbstractConverter<Double, BigDecimal>() {
            @Override
            protected BigDecimal convert(Double source) {
                return new BigDecimal(String.valueOf(source));
            }
        });

        modelMapper.createTypeMap(BigDecimal.class, Double.class);
        modelMapper.addConverter(new AbstractConverter<BigDecimal, Double>() {
            @Override
            protected Double convert(BigDecimal source) {
                return source.doubleValue();
            }
        });

        modelMapper.createTypeMap(Integer.class, BigDecimal.class);
        modelMapper.addConverter(new AbstractConverter<Integer, BigDecimal>() {
            @Override
            protected BigDecimal convert(Integer source) {
                return new BigDecimal(String.valueOf(source));
            }
        });

        modelMapper.createTypeMap(BigDecimal.class, Integer.class);
        modelMapper.addConverter(new AbstractConverter<BigDecimal, Integer>() {
            @Override
            protected Integer convert(BigDecimal source) {
                return source.intValue();
            }
        });
    }

}
