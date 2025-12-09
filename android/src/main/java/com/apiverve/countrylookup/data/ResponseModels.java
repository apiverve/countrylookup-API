// Converter.java

// To use this code, add the following Maven dependency to your project:
//
//
//     com.fasterxml.jackson.core     : jackson-databind          : 2.9.0
//     com.fasterxml.jackson.datatype : jackson-datatype-jsr310   : 2.9.0
//
// Import this package:
//
//     import com.apiverve.data.Converter;
//
// Then you can deserialize a JSON string with
//
//     CountryLookupData data = Converter.fromJsonString(jsonString);

package com.apiverve.countrylookup.data;

import java.io.IOException;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.*;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.OffsetTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;

public class Converter {
    // Date-time helpers

    private static final DateTimeFormatter DATE_TIME_FORMATTER = new DateTimeFormatterBuilder()
            .appendOptional(DateTimeFormatter.ISO_DATE_TIME)
            .appendOptional(DateTimeFormatter.ISO_OFFSET_DATE_TIME)
            .appendOptional(DateTimeFormatter.ISO_INSTANT)
            .appendOptional(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SX"))
            .appendOptional(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ssX"))
            .appendOptional(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
            .toFormatter()
            .withZone(ZoneOffset.UTC);

    public static OffsetDateTime parseDateTimeString(String str) {
        return ZonedDateTime.from(Converter.DATE_TIME_FORMATTER.parse(str)).toOffsetDateTime();
    }

    private static final DateTimeFormatter TIME_FORMATTER = new DateTimeFormatterBuilder()
            .appendOptional(DateTimeFormatter.ISO_TIME)
            .appendOptional(DateTimeFormatter.ISO_OFFSET_TIME)
            .parseDefaulting(ChronoField.YEAR, 2020)
            .parseDefaulting(ChronoField.MONTH_OF_YEAR, 1)
            .parseDefaulting(ChronoField.DAY_OF_MONTH, 1)
            .toFormatter()
            .withZone(ZoneOffset.UTC);

    public static OffsetTime parseTimeString(String str) {
        return ZonedDateTime.from(Converter.TIME_FORMATTER.parse(str)).toOffsetDateTime().toOffsetTime();
    }
    // Serialize/deserialize helpers

    public static CountryLookupData fromJsonString(String json) throws IOException {
        return getObjectReader().readValue(json);
    }

    public static String toJsonString(CountryLookupData obj) throws JsonProcessingException {
        return getObjectWriter().writeValueAsString(obj);
    }

    private static ObjectReader reader;
    private static ObjectWriter writer;

    private static void instantiateMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        SimpleModule module = new SimpleModule();
        module.addDeserializer(OffsetDateTime.class, new JsonDeserializer<OffsetDateTime>() {
            @Override
            public OffsetDateTime deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
                String value = jsonParser.getText();
                return Converter.parseDateTimeString(value);
            }
        });
        mapper.registerModule(module);
        reader = mapper.readerFor(CountryLookupData.class);
        writer = mapper.writerFor(CountryLookupData.class);
    }

    private static ObjectReader getObjectReader() {
        if (reader == null) instantiateMapper();
        return reader;
    }

    private static ObjectWriter getObjectWriter() {
        if (writer == null) instantiateMapper();
        return writer;
    }
}

// CountryLookupData.java

package com.apiverve.countrylookup.data;

import com.fasterxml.jackson.annotation.*;

public class CountryLookupData {
    private String search;
    private Country[] countries;

    @JsonProperty("search")
    public String getSearch() { return search; }
    @JsonProperty("search")
    public void setSearch(String value) { this.search = value; }

    @JsonProperty("countries")
    public Country[] getCountries() { return countries; }
    @JsonProperty("countries")
    public void setCountries(Country[] value) { this.countries = value; }
}

// Country.java

package com.apiverve.countrylookup.data;

import com.fasterxml.jackson.annotation.*;

public class Country {
    private Name name;
    private String[] tld;
    private String cca2;
    private String ccn3;
    private String cca3;
    private String cioc;
    private boolean independent;
    private String status;
    private Currencies currencies;
    private String[] capital;
    private String[] altSpellings;
    private String region;
    private String subregion;
    private Languages languages;
    private long[] latlng;
    private boolean landlocked;
    private String[] borders;
    private long area;
    private String flag;
    private String[] majorCities;

    @JsonProperty("name")
    public Name getName() { return name; }
    @JsonProperty("name")
    public void setName(Name value) { this.name = value; }

    @JsonProperty("tld")
    public String[] getTLD() { return tld; }
    @JsonProperty("tld")
    public void setTLD(String[] value) { this.tld = value; }

    @JsonProperty("cca2")
    public String getCca2() { return cca2; }
    @JsonProperty("cca2")
    public void setCca2(String value) { this.cca2 = value; }

    @JsonProperty("ccn3")
    public String getCcn3() { return ccn3; }
    @JsonProperty("ccn3")
    public void setCcn3(String value) { this.ccn3 = value; }

    @JsonProperty("cca3")
    public String getCca3() { return cca3; }
    @JsonProperty("cca3")
    public void setCca3(String value) { this.cca3 = value; }

    @JsonProperty("cioc")
    public String getCioc() { return cioc; }
    @JsonProperty("cioc")
    public void setCioc(String value) { this.cioc = value; }

    @JsonProperty("independent")
    public boolean getIndependent() { return independent; }
    @JsonProperty("independent")
    public void setIndependent(boolean value) { this.independent = value; }

    @JsonProperty("status")
    public String getStatus() { return status; }
    @JsonProperty("status")
    public void setStatus(String value) { this.status = value; }

    @JsonProperty("currencies")
    public Currencies getCurrencies() { return currencies; }
    @JsonProperty("currencies")
    public void setCurrencies(Currencies value) { this.currencies = value; }

    @JsonProperty("capital")
    public String[] getCapital() { return capital; }
    @JsonProperty("capital")
    public void setCapital(String[] value) { this.capital = value; }

    @JsonProperty("altSpellings")
    public String[] getAltSpellings() { return altSpellings; }
    @JsonProperty("altSpellings")
    public void setAltSpellings(String[] value) { this.altSpellings = value; }

    @JsonProperty("region")
    public String getRegion() { return region; }
    @JsonProperty("region")
    public void setRegion(String value) { this.region = value; }

    @JsonProperty("subregion")
    public String getSubregion() { return subregion; }
    @JsonProperty("subregion")
    public void setSubregion(String value) { this.subregion = value; }

    @JsonProperty("languages")
    public Languages getLanguages() { return languages; }
    @JsonProperty("languages")
    public void setLanguages(Languages value) { this.languages = value; }

    @JsonProperty("latlng")
    public long[] getLatlng() { return latlng; }
    @JsonProperty("latlng")
    public void setLatlng(long[] value) { this.latlng = value; }

    @JsonProperty("landlocked")
    public boolean getLandlocked() { return landlocked; }
    @JsonProperty("landlocked")
    public void setLandlocked(boolean value) { this.landlocked = value; }

    @JsonProperty("borders")
    public String[] getBorders() { return borders; }
    @JsonProperty("borders")
    public void setBorders(String[] value) { this.borders = value; }

    @JsonProperty("area")
    public long getArea() { return area; }
    @JsonProperty("area")
    public void setArea(long value) { this.area = value; }

    @JsonProperty("flag")
    public String getFlag() { return flag; }
    @JsonProperty("flag")
    public void setFlag(String value) { this.flag = value; }

    @JsonProperty("majorCities")
    public String[] getMajorCities() { return majorCities; }
    @JsonProperty("majorCities")
    public void setMajorCities(String[] value) { this.majorCities = value; }
}

// Currencies.java

package com.apiverve.countrylookup.data;

import com.fasterxml.jackson.annotation.*;

public class Currencies {
    private Usd usd;

    @JsonProperty("USD")
    public Usd getUsd() { return usd; }
    @JsonProperty("USD")
    public void setUsd(Usd value) { this.usd = value; }
}

// Usd.java

package com.apiverve.countrylookup.data;

import com.fasterxml.jackson.annotation.*;

public class Usd {
    private String name;
    private String symbol;

    @JsonProperty("name")
    public String getName() { return name; }
    @JsonProperty("name")
    public void setName(String value) { this.name = value; }

    @JsonProperty("symbol")
    public String getSymbol() { return symbol; }
    @JsonProperty("symbol")
    public void setSymbol(String value) { this.symbol = value; }
}

// Languages.java

package com.apiverve.countrylookup.data;

import com.fasterxml.jackson.annotation.*;

public class Languages {
    private String eng;

    @JsonProperty("eng")
    public String getEng() { return eng; }
    @JsonProperty("eng")
    public void setEng(String value) { this.eng = value; }
}

// Name.java

package com.apiverve.countrylookup.data;

import com.fasterxml.jackson.annotation.*;

public class Name {
    private String common;
    private String official;
    private Native nameNative;

    @JsonProperty("common")
    public String getCommon() { return common; }
    @JsonProperty("common")
    public void setCommon(String value) { this.common = value; }

    @JsonProperty("official")
    public String getOfficial() { return official; }
    @JsonProperty("official")
    public void setOfficial(String value) { this.official = value; }

    @JsonProperty("native")
    public Native getNameNative() { return nameNative; }
    @JsonProperty("native")
    public void setNameNative(Native value) { this.nameNative = value; }
}

// Native.java

package com.apiverve.countrylookup.data;

import com.fasterxml.jackson.annotation.*;

public class Native {
    private Eng eng;

    @JsonProperty("eng")
    public Eng getEng() { return eng; }
    @JsonProperty("eng")
    public void setEng(Eng value) { this.eng = value; }
}

// Eng.java

package com.apiverve.countrylookup.data;

import com.fasterxml.jackson.annotation.*;

public class Eng {
    private String official;
    private String common;

    @JsonProperty("official")
    public String getOfficial() { return official; }
    @JsonProperty("official")
    public void setOfficial(String value) { this.official = value; }

    @JsonProperty("common")
    public String getCommon() { return common; }
    @JsonProperty("common")
    public void setCommon(String value) { this.common = value; }
}