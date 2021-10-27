package christinagorina.homework.utils;

import christinagorina.homework.domain.LocaleMap;

import java.util.Map;

public class Util {
    public static Map<String, String> fillInLocaleMap(LocaleMap localeMap) {
        Map<String, String> langKeyMap = localeMap.getLangKeyMap();
        langKeyMap.put("информатика", "strings.informatics");
        langKeyMap.put("черный", "strings.black");
        langKeyMap.put("крис", "strings.kris");
        langKeyMap.put("джава", "strings.java");
        langKeyMap.put("программист", "strings.programmer");
        langKeyMap.put("whatisthenameofyourfaculty", "strings.nameoffaculty");
        langKeyMap.put("whatcolorofthetextbook", "strings.coloroftextbook");
        langKeyMap.put("whatisyourname", "strings.yourname");
        langKeyMap.put("whatlanguagedoyoustudy", "strings.studylanguage");
        langKeyMap.put("whatisyourprofession", "strings.yourprofession");
        return langKeyMap;
    }
}
