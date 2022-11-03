package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.RenameCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parser to rename a item for a displayitem
 */
public class RenameCommandParser implements Parser<RenameCommand> {
    private static final Pattern ADD_ATTRIBUTE_COMMAND_FORMAT = Pattern
        .compile("(?<type>[ugt])/(?<id>[0-9]+)\\s+(?<newname>.+)");

    @Override
    public RenameCommand parse(String args) throws ParseException {
        final Matcher matcher = ADD_ATTRIBUTE_COMMAND_FORMAT.matcher(args.trim());

        if (args.trim().length() == 0) {
            throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, RenameCommand.MESSAGE_USAGE));
        }

        if (!matcher.matches()) {
            return new RenameCommand(args.trim());
        }

        Index index = ParserUtil.parseIndex(matcher.group("id").trim());

        String newName = matcher.group("newname");
        String type = matcher.group("type");
        ParserUtil.parseName(newName);

        switch (type) {
        case "u":
            return new RenameCommand(index, 2, newName);
        case "g":
            return new RenameCommand(index, 1, newName);
        case "t":
            return new RenameCommand(index, 3, newName);
        default:
            assert false;
        }

        throw new ParseException(
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, RenameCommand.MESSAGE_USAGE));
    }
}
