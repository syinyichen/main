package seedu.address.logic.parser.wallet;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.wallet.WalletFindCommand;
import seedu.address.model.transaction.DescriptionContainsKeywordsPredicate;

public class WalletFindCommandParserTest {
    private WalletFindCommandParser parser = new WalletFindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                WalletFindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        WalletFindCommand expectedWalletFindCommand =
                new WalletFindCommand(new DescriptionContainsKeywordsPredicate(Arrays.asList("Chicken Rice", "Duck "
                        + "Rice")));
        assertParseSuccess(parser, "Chicken Duck", expectedWalletFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n Chicken \n \t Duck  \t", expectedWalletFindCommand);
    }

}
