package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.AddressBookParser;
import seedu.address.model.Model;

/**
 * Class with the ability to build a custom commands
 */
public class CustomCommandBuilder {
    private final String repr;
    private final String commandData;

    /**
     * Creates a custom command
     *
     * @param repr name of command
     * @param commandData command details
     */
    public CustomCommandBuilder(String repr, String commandData) {
        this.repr = repr;
        this.commandData = commandData;
    }

    /**
     * Retrieves the macro shortcut that represent this command
     *
     * @return
     */
    public String getRepr() {
        return repr;
    }

    /**
     * Builds the custom command based on the stored information
     */
    public Command build() {
        return new Command() {
            private Object o = null;

            @Override
            public Command setInput(Object additionalData) throws CommandException {
                o = additionalData;
                return this;
            }

            @Override
            public CommandResult execute(Model model) throws CommandException {
                return AddressBookParser.quickCommand(commandData, o).execute(model);
            }
        };
    }

    @Override
    public int hashCode() {
        return repr.hashCode() ^ commandData.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof CustomCommandBuilder)
            && ((CustomCommandBuilder) obj).repr.equals(repr)
            && ((CustomCommandBuilder) obj).commandData.equals(commandData);
    }
}
