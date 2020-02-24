package seedu.address.logic.parser.people;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_AMOUNT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DATE;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.OWE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.OWE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_AMOUNT_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_AMOUNT_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.people.PeopleOweCommand;
import seedu.address.model.transaction.Amount;
import seedu.address.model.transaction.Debt;

public class PeopleOweCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, PeopleOweCommand.MESSAGE_USAGE);
    private PeopleOweCommandParser parser = new PeopleOweCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_NAME_AMY, MESSAGE_INVALID_FORMAT);

        // no amount specified
        assertParseFailure(parser, "1", MESSAGE_INVALID_FORMAT);

        // no index and amount specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + NAME_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + NAME_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_AMOUNT, Amount.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + VALID_AMOUNT_AMY + INVALID_DATE, MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser, "1" + INVALID_AMOUNT + VALID_DATE_AMY, Amount.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + INVALID_AMOUNT + INVALID_DATE, Amount.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_PERSON;
        String userInput = targetIndex.getOneBased() + OWE_DESC_AMY;

        Debt debt = new Debt(new Amount(Double.parseDouble(VALID_AMOUNT_AMY)), LocalDate.parse(VALID_DATE_AMY,
                DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        PeopleOweCommand expectedCommand = new PeopleOweCommand(targetIndex, debt);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_dateNotSpecified_success() {
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + OWE_DESC_BOB;

        Debt debt = new Debt(new Amount(Double.parseDouble(VALID_AMOUNT_BOB)), LocalDate.now());
        PeopleOweCommand expectedCommand = new PeopleOweCommand(targetIndex, debt);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

}
