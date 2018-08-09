package com.mylearn.bdd.runner;

import com.mylearn.bdd.steps.StudentSteps;
import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.io.StoryFinder;
import org.jbehave.core.junit.JUnitStories;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.CandidateSteps;
import org.jbehave.core.steps.InstanceStepsFactory;

import java.util.List;

import static org.jbehave.core.io.CodeLocations.codeLocationFromClass;

/**
 * Created with IntelliJ IDEA.
 * User: twer
 * Date: 5/18/12
 * Time: 5:11 PM
 */
public class StudentStories extends JUnitStories {

    public Configuration configuration() {
        return new MostUsefulConfiguration().useStoryLoader(new LoadFromClasspath(this.getClass()))
                .useStoryReporterBuilder(new StoryReporterBuilder().withCodeLocation(codeLocationFromClass(this.getClass())));

    }

    public List<CandidateSteps> candidateSteps(){
        return new InstanceStepsFactory(configuration(),new StudentSteps()).createCandidateSteps();
    }

//    public Embedder configuredEmbedder() {
//        Embedder embedder = new Embedder();
//        embedder.useConfiguration(configuration());
//        embedder.useCandidateSteps(candidateSteps());
//        embedder.useStepsFactory(stepsFactory());
//        embedder.useMetaFilters(asList("+theme composite", "-skip"));
//        return embedder;
//    }

    @Override
    protected List<String> storyPaths() {
        return new StoryFinder().findPaths(codeLocationFromClass(this.getClass()),"**/*.story","");

    }
}
