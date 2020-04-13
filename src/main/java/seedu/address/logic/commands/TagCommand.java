package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Model;

/** Clears the task list. */
public class TagCommand extends Command {

    public static final String COMMAND_WORD = "tag";
    public static final String MESSAGE_SUCCESS = "You have these tags:\n";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        String[] tagNames = model.getTagNames();
        return new CommandResult(MESSAGE_SUCCESS + String.join("\n", tagNames));
    }
}