package ch.prinzbach.jass.validation;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class JassCardValidatorTest {

    private JassCardValidator jassCardValidator;
    private List<JassCard> alreadyPlayedCards;
    private Set<JassCard> playersCards;

    @Before
    public void setUp() {
        jassCardValidator = new JassCardValidator();
        alreadyPlayedCards = new ArrayList<>();
        playersCards = new HashSet<>();
    }

    @Test
    public void validateCard_playerHasCardInHand() {
        final JassCard cardToValidate = new JassCard(6, CardColor.HEARTS);
        playersCards.add(cardToValidate);

        boolean isCardValid = jassCardValidator.validateCard(alreadyPlayedCards, cardToValidate, playersCards);

        assertTrue(isCardValid);
    }

    @Test
    public void validateCard_playerHasCardNotInHand() {
        final JassCard cardToValidate = new JassCard(6, CardColor.HEARTS);

        boolean isCardValid = jassCardValidator.validateCard(alreadyPlayedCards, cardToValidate, playersCards);

        assertFalse(isCardValid);
    }

    @Test
    public void validateCard_playerShouldHavePlayedSameColor() {
        final JassCard cardToValidate = new JassCard(6, CardColor.HEARTS);
        playersCards.add(cardToValidate);
        playersCards.add(new JassCard(10, CardColor.CLUBS));
        alreadyPlayedCards.add(new JassCard(7, CardColor.CLUBS));

        boolean isCardValid = jassCardValidator.validateCard(alreadyPlayedCards, cardToValidate, playersCards);

        assertFalse(isCardValid);
    }

    @Test
    public void validateCard_playerHasCardsButNotSameColor() {
        final JassCard cardToValidate = new JassCard(6, CardColor.HEARTS);
        playersCards.add(cardToValidate);
        alreadyPlayedCards.add(new JassCard(7, CardColor.CLUBS));

        boolean isCardValid = jassCardValidator.validateCard(alreadyPlayedCards, cardToValidate, playersCards);

        assertTrue(isCardValid);
    }

    @Test
    public void validateCard_playerHasCardAndPlayedCorrectColor() {
        final JassCard cardToValidate = new JassCard(6, CardColor.HEARTS);
        playersCards.add(cardToValidate);
        alreadyPlayedCards.add(new JassCard(7, CardColor.HEARTS));

        boolean isCardValid = jassCardValidator.validateCard(alreadyPlayedCards, cardToValidate, playersCards);

        assertTrue(isCardValid);
    }

}
