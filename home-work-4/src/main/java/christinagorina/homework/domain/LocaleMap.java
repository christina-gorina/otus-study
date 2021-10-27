package christinagorina.homework.domain;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class LocaleMap {
    public Map<String, String> langKeyMap = new HashMap<>();
}
