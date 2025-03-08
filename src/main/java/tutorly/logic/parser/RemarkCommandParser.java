package tutorly.logic.parser;

import static java.util.Objects.requireNonNull;

import tutorly.commons.core.index.Index;
import tutorly.logic.commands.Command;
import tutorly.logic.commands.RemarkCommand;
import tutorly.logic.parser.exceptions.ParseException;

public class RemarkCommandParser {
    public static final String MESSAGE_INVALID_REMARK_COMMAND_FORMAT = "Invalid command format! \n%1$s";

    /**
     * Parses the given {@code String} of arguments in the context of the RemarkCommand
     * and returns a RemarkCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, CliSyntax.PREFIX_REMARK);

        if (!argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_REMARK_COMMAND_FORMAT, RemarkCommand.MESSAGE_USAGE));
        }

        String remark = argMultimap.getValue(CliSyntax.PREFIX_REMARK).get();

        Index index = ParserUtil.parseIndex(argMultimap.getPreamble());

        return new RemarkCommand(index, remark);
    }
}
