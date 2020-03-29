package seedu.address.logic.parser.people;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_KEYWORD_NOT_FOUND;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.people.PeopleFindCommand;
import seedu.address.model.person.PeopleEmailPredicate;
import seedu.address.model.person.PeopleNamePredicate;
import seedu.address.model.person.PeoplePhonePredicate;
import seedu.address.model.person.PeopleTagPredicate;

public class PeopleFindCommandParserTest {

    private PeopleFindCommandParser parser = new PeopleFindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                PeopleFindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidInput_throwsParseException() {
        assertParseFailure(parser, " alex", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                PeopleFindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_moreThanOneParameter_throwsParseException() {
        assertParseFailure(parser, " n/Alice p/91234567",
                String.format(PeopleFindCommand.ONLY_ONE_PARAMETER_ALLOWED, PeopleFindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_noKeyword_throwsParseException() {
        assertParseFailure(parser, " n/",
                String.format(MESSAGE_KEYWORD_NOT_FOUND, PeopleFindCommand.MESSAGE_USAGE));
        assertParseFailure(parser, " p/",
                String.format(MESSAGE_KEYWORD_NOT_FOUND, PeopleFindCommand.MESSAGE_USAGE));
        assertParseFailure(parser, " e/",
                String.format(MESSAGE_KEYWORD_NOT_FOUND, PeopleFindCommand.MESSAGE_USAGE));
        assertParseFailure(parser, " t/",
                String.format(MESSAGE_KEYWORD_NOT_FOUND, PeopleFindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_prefixName_returnsFindCommand() {
        // no leading and trailing whitespaces
        PeopleFindCommand expectedPeopleFindCommand =
                new PeopleFindCommand(new PeopleNamePredicate(Arrays.asList("Alice", "Bob")));
        assertParseSuccess(parser, " n/Alice Bob", expectedPeopleFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " n/ \n Alice \n \t Bob  \t", expectedPeopleFindCommand);
    }

    @Test
    public void parse_prefixPhone_returnsFindCommand() {
        // no leading and trailing whitespaces
        PeopleFindCommand expectedPeopleFindCommand =
                new PeopleFindCommand(new PeoplePhonePredicate(Arrays.asList("9123", "1234")));
        assertParseSuccess(parser, " p/9123 1234", expectedPeopleFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " p/ \n 9123 \n \t 1234  \t", expectedPeopleFindCommand);
    }

    @Test
    public void parse_prefixEmail_returnsFindCommand() {
        // no leading and trailing whitespaces
        PeopleFindCommand expectedPeopleFindCommand =
                new PeopleFindCommand(new PeopleEmailPredicate(Arrays.asList("test@example.com", "gmail")));
        assertParseSuccess(parser, " e/test@example.com gmail", expectedPeopleFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " e/ \n test@example.com \n \t gmail  \t", expectedPeopleFindCommand);
    }

    @Test
    public void parse_prefixTag_returnsFindCommand() {
        // no leading and trailing whitespaces
        PeopleFindCommand expectedPeopleFindCommand =
                new PeopleFindCommand(new PeopleTagPredicate(Arrays.asList("friend", "colleague")));
        assertParseSuccess(parser, " t/friend colleague", expectedPeopleFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " t/ \n friend \n \t colleague  \t", expectedPeopleFindCommand);
    }

}
