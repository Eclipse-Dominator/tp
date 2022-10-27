package seedu.address.model.attribute;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.AccessDisplayFlags;

public class CustomAttributeMaker {

    private static final String TYPE_ERROR = "The type must be of begin with a letter and can contain only letter, numbers, _ or -";
    private static final String PREFIX_ERROR = "Prefix can only contain letters and must end with /";
    private static final String TYPE_CHECK = "[A-Za-z][a-zA-Z0-9_-]*";
    private static final String PREFIX_CHECK = "[A-Za-z]+\\/";

    private final String type;
    private final int accessSetting;
    private final int displaySetting;
    private final String regexCheck;
    private final String prefix;

    private CustomAttributeMaker(String type, int accessSetting, int displaySetting, String regexCheck, String prefix) {  
        this.type = type;
        this.accessSetting = accessSetting;
        this.displaySetting = displaySetting;
        this.regexCheck = regexCheck;
        this.prefix = prefix;
    }

    private CustomAttributeMaker(String type, int accessSetting, int displaySetting, String regexCheck) {  
        this(type, accessSetting, displaySetting, regexCheck, "");
    }

    private CustomAttributeMaker(String type) {
        this(type, AccessDisplayFlags.DEFAULT, AccessDisplayFlags.DEFAULT_STYLE, "");
    }

    /**
     * Generate an attribute based on the defined settings
     */
    public Attribute<String> build(String value) throws ParseException {
        if (!regexCheck.isEmpty() && !value.matches(regexCheck)) {
            throw new ParseException(String.format("Attribute need to be of form %s", regexCheck));
        }

        return new AbstractAttribute<String>(type,prefix,value,accessSetting,displaySetting) {
        };
    }

    /**
     * Retrieves the prefix of this attribute
     * @return
     */
    public String getPrefix() {
        return this.prefix;
    }

    @Override
    public boolean equals(Object obj) {
        if (! (obj instanceof CustomAttributeMaker)) {
            return false;
        }
        return type.equals(((CustomAttributeMaker) obj).type);
    }

    @Override
    public int hashCode() {
        return type.hashCode() ^ accessSetting ^ displaySetting ^ regexCheck.hashCode();
    }

    private static boolean isValidPrefix(String prefix) {
        return prefix.matches(PREFIX_CHECK) || prefix.equals("");
    }

    private static boolean isValidTypeName(String type) {
        return type.matches(TYPE_CHECK);
    }

    /**
     * Factory method to createa a custom attribute maker.
     * 
     * @return
     * @throws ParseException
     */
    public static CustomAttributeMaker makeAttribute(String type, int accessSetting, int displaySetting, String regexCheck, String prefix) throws ParseException {
        if (!isValidTypeName(type)) {
            throw new ParseException(TYPE_ERROR);
        }
        if (!isValidPrefix(prefix)) {
            throw new ParseException(PREFIX_ERROR);
        }
        return new CustomAttributeMaker(type, accessSetting, displaySetting, regexCheck, prefix);
    }

    public static CustomAttributeMaker makeAttribute(String type, String prefix) throws ParseException {
        return makeAttribute(type, AccessDisplayFlags.DEFAULT, AccessDisplayFlags.DEFAULT_STYLE, "", prefix);
    }

    public static CustomAttributeMaker makeAttribute(String type) throws ParseException {
        return makeAttribute(type, AccessDisplayFlags.DEFAULT, AccessDisplayFlags.DEFAULT_STYLE, "", "");
    }
}
