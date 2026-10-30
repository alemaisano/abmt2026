package org.eth.week6.exercises.mode_choice.estimators;

import java.util.List;

import org.eqasim.core.simulation.mode_choice.utilities.estimators.BikeUtilityEstimator;
import org.eqasim.core.simulation.mode_choice.utilities.predictors.BikePredictor;
import org.eqasim.core.simulation.mode_choice.utilities.predictors.PersonPredictor;
import org.eqasim.core.simulation.mode_choice.utilities.variables.BikeVariables;
import org.eqasim.core.simulation.mode_choice.utilities.variables.PersonVariables;
import org.matsim.api.core.v01.population.Person;
import org.matsim.api.core.v01.population.PlanElement;
import org.matsim.contribs.discrete_mode_choice.model.DiscreteModeChoiceTrip;

import com.google.inject.Inject;

import org.eth.week6.exercises.mode_choice.parameters.AbmtModeParameters;

/**
 * ABMT-specific utility estimator for bike trips.
 *
 * This class currently uses eqasim's standard bike utility calculation while
 * accepting the project's custom mode parameters. It can be extended with
 * ABMT-specific bike utility terms in the same way as
 * {@link AbmtCarUtilityEstimator}.
 */
public class AbmtBikeUtilityEstimator extends BikeUtilityEstimator {

    private final AbmtModeParameters parameters;
    private final PersonPredictor personPredictor;
    private final BikePredictor bikePredictor;

    @Inject
    public AbmtBikeUtilityEstimator(AbmtModeParameters parameters, PersonPredictor personPredictor,
            BikePredictor bikePredictor) {
        super(parameters, personPredictor, bikePredictor);
        this.parameters = parameters;
        this.personPredictor = personPredictor;
        this.bikePredictor = bikePredictor;
    }

    protected double estimateAgeOver64Utility(PersonVariables personVariables) {
        return personVariables.age_a > 64.0 ? parameters.abmtBike.beta_H : 0.0;
    }

    @Override
    public double estimateUtility(Person person, DiscreteModeChoiceTrip trip, List<? extends PlanElement> elements) {
        PersonVariables personVariables = personPredictor.predictVariables(person, trip, elements);
        BikeVariables bikeVariables = bikePredictor.predictVariables(person, trip, elements);

        double utility = 0.0;
        utility += estimateConstantUtility();
        utility += estimateTravelTimeUtility(bikeVariables);
        utility += estimateAgeOver18Utility(personVariables);
        utility += estimateAgeOver64Utility(personVariables);

        return utility;
    }
}
