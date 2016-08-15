package org.hanna.woodbadge.gameshow;

import org.hanna.woodbadge.gameshow.model.Category;
import org.hanna.woodbadge.gameshow.model.Critter;
import org.hanna.woodbadge.gameshow.model.Question;
import org.hanna.woodbadge.gameshow.model.QuestionKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collection;
import java.util.Map;

/**
 * Created by timhanna on 5/16/16.
 */
@Controller
@EnableAutoConfiguration
@ComponentScan
public class GameShowController {

    @Autowired
    private GameShowService gameShowService;

    @RequestMapping("/questions/{round}/{category}/{points}")
    @ResponseBody
    public Question getQuestion(@PathVariable("round") int round,
                                @PathVariable("category") Category category,
                                @PathVariable("points") int points) {
        return gameShowService.getQuestion(round, category, points);
    }

    @RequestMapping("/questions/{round}")
    @ResponseBody
    public Map<Category, Collection<Question>> getQuestion(@PathVariable("round") int round) {
        return gameShowService.getQuestions(round);
    }

    @RequestMapping(value = "/score/{round}/{category}/{points}/{critter}/correct", method = RequestMethod.POST)
    @ResponseBody
    public void addPoints(@PathVariable("critter") Critter critter,
                          @PathVariable("round") int round,
                          @PathVariable("category") Category category,
                          @PathVariable("points") int points) {
        gameShowService.correctScore(critter, points);
        gameShowService.answerQuestion(round, category, points);
    }

    @RequestMapping(value = "/score/{round}/{category}/{points}/{critter}/incorrect", method = RequestMethod.POST)
    @ResponseBody
    public void removePoints(@PathVariable("critter") Critter critter,
                             @PathVariable("round") int round,
                             @PathVariable("category") Category category,
                             @PathVariable("points") int points) {
        gameShowService.incorrectScore(critter, points);
        gameShowService.answerQuestion(round, category, points);
    }


    @RequestMapping(value = "/score/{round}/{category}/{points}/{critter}/noAnswer", method = RequestMethod.POST)
    @ResponseBody
    public void noPoints(@PathVariable("critter") Critter critter,
                             @PathVariable("round") int round,
                             @PathVariable("category") Category category,
                             @PathVariable("points") int points) {
        Question question = gameShowService.getQuestion(round, category, points);
        question.setAnswered(true);
    }

    @RequestMapping(value = "/score/final//{points}/{critter}/{result}", method = RequestMethod.POST)
    @ResponseBody
    public void finalQuestion(@PathVariable("critter") Critter critter,
                              @PathVariable("points") int points,
                              @PathVariable("result") Boolean result) {
        gameShowService.finalQuestion(critter, points, result);
    }

    @RequestMapping(value="/score")
    @ResponseBody
    public Map<Critter, Integer> getScore() {
        return gameShowService.getScore();
    }
}
