package seedu.address.model.attribute.exceptions;

/**
 * Encapsulates an AttributeNotFoundException
 */
public class AttributeCannotBeAddedException extends AttributeException {

    private static final String MESSAGE = "Attribute %s cannot be added this item";

    /**
     * Cosntructs an AttributeNotFoundException
     *
     * @param attributeName
     */
    public AttributeCannotBeAddedException(String attributeName) {
        super(String.format(MESSAGE, attributeName));
    }

}

