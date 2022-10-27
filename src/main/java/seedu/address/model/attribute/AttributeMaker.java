package seedu.address.model.attribute;

import java.text.ParseException;

/**
 * An interface to create an attribute
 */
public interface AttributeMaker<T> {
    /**
     * Creates the corresponding attribute specified by the attribute maker
     * @param value
     * @return
     * @throws ParseException
     */
    Attribute<T> build(T value) throws ParseException;

    /**
     * Retrieves the prefix of the attribute
     * @return
     */
    String getPrefix();
}
