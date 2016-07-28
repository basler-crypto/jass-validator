package ch.prinzbach.jass.validation;

import ch.prinzbach.jass.domain.JassCard;
import ch.prinzbach.jass.domain.JassTable;
import ch.prinzbach.jass.validation.validator.JassCardValidator;
import ch.prinzbach.jass.validation.validator.PlayerHasCardValidator;
import ch.prinzbach.jass.validation.validator.PlayerNeedsToPlayCorrectColorValidator;

import java.util.HashSet;
import java.util.Set;

public class JassCardValidationEngine {

    private final Set<JassCardValidator> validators = new HashSet<>();

    public JassCardValidationEngine() {
        validators.add(new PlayerHasCardValidator());
        validators.add(new PlayerNeedsToPlayCorrectColorValidator());
    }

    public boolean validateCard(JassTable jassTable, JassCard cardToValidate, Set<JassCard> playersCards) {
        return validators.stream()
                .map(validator -> validator.validate(jassTable, cardToValidate, playersCards))
                .allMatch(valid -> valid);
    }

}
