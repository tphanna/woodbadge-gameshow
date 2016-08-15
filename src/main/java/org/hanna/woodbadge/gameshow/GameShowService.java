package org.hanna.woodbadge.gameshow;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.MultimapBuilder;
import com.google.common.collect.TreeMultimap;
import org.hanna.woodbadge.gameshow.model.Category;
import org.hanna.woodbadge.gameshow.model.Critter;
import org.hanna.woodbadge.gameshow.model.Question;
import org.hanna.woodbadge.gameshow.model.QuestionKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by timhanna on 5/16/16.
 */
@Service
public class GameShowService {

    private Map<QuestionKey, Question> questions;
    private Map<Critter, Integer> score;
    @Autowired
    private GameShowRepository repository;

    public GameShowService() {

        score = new HashMap<>();
        score.put(Critter.BEAVER, 0);
        score.put(Critter.BOBWHITE, 0);
        score.put(Critter.EAGLE, 0);
        score.put(Critter.FOX, 0);
        score.put(Critter.OWL, 0);
        score.put(Critter.BEAR, 0);
        score.put(Critter.BUFFALO, 0);
        score.put(Critter.ANTELOPE, 0);

        questions = new HashMap<>();
        //round 1
        questions.put(new QuestionKey(1, Category.CUBS, 100), new Question(1, Category.CUBS, 100, "What is the cub scout motto?"));
        questions.put(new QuestionKey(1, Category.CUBS, 200), new Question(1, Category.CUBS, 200, "What is the minimum amount of time that a boy needs ot be active as a Webelos Scout before he can earn the Webelos badge?"));
        questions.put(new QuestionKey(1, Category.CUBS, 300), new Question(1, Category.CUBS, 300, "How many years must a den leader serve before he/she can earn the Den Leader Training Award?"));
        questions.put(new QuestionKey(1, Category.CUBS, 400), new Question(1, Category.CUBS, 400, "Which Cub Scout adventure is required at each rank?"));

        questions.put(new QuestionKey(1, Category.SCOUTS, 100), new Question(1, Category.SCOUTS, 100, "Is a troop guide a member of the patrol leaders' council?"));
        questions.put(new QuestionKey(1, Category.SCOUTS, 200), new Question(1, Category.SCOUTS, 200, "What rank is the goal of the Boy Scouts of America that every Scout should earn?"));
        questions.put(new QuestionKey(1, Category.SCOUTS, 300), new Question(1, Category.SCOUTS, 300, "What is the proper way for a Boy Scout to salute the flag when he is out of uniform?"));
        questions.put(new QuestionKey(1, Category.SCOUTS, 400), new Question(1, Category.SCOUTS, 400, "If the Scouts in a troop wear a neckerchief, are the adult leaders allowed to do so as well?"));

        questions.put(new QuestionKey(1, Category.VARSITY, 100), new Question(1, Category.VARSITY, 100, "What is a Varsity Scout unit called?"));
        questions.put(new QuestionKey(1, Category.VARSITY, 200), new Question(1, Category.VARSITY, 200, "What ages are boys in a Varsity Scouting?"));
        questions.put(new QuestionKey(1, Category.VARSITY, 300), new Question(1, Category.VARSITY, 300, "In addition to the Varsity Letter, what award can be earned only by Varsity Scouts?"));
        questions.put(new QuestionKey(1, Category.VARSITY, 400), new Question(1, Category.VARSITY, 400, "Name two of the five fields of emphasis in Varsity Scouting."));

        questions.put(new QuestionKey(1, Category.VENTURE, 100), new Question(1, Category.VENTURE, 100, "What is the first advancement award specific to the Venturing program tha a Venturer may earn?"));
        questions.put(new QuestionKey(1, Category.VENTURE, 200), new Question(1, Category.VENTURE, 200, "Venturing crews can be made up of three different gender groupings. What are they?"));
        questions.put(new QuestionKey(1, Category.VENTURE, 300), new Question(1, Category.VENTURE, 300, "Can a Venturer be elected to the Order of the Arrow by the rest of the crew?"));
        questions.put(new QuestionKey(1, Category.VENTURE, 400), new Question(1, Category.VENTURE, 400, "What parts of the Venturing uniform must be worn by all members of a particular Venturing crew?"));

        questions.put(new QuestionKey(1, Category.DISTRICT, 100), new Question(1, Category.DISTRICT, 100, "Can a district identification patch or district insignia be worn on a BSA uniform?"));
        questions.put(new QuestionKey(1, Category.DISTRICT, 200), new Question(1, Category.DISTRICT, 200, "Who does the district commissioner \"work\" for?"));
        questions.put(new QuestionKey(1, Category.DISTRICT, 300), new Question(1, Category.DISTRICT, 300, "Who make up the Key 3 in a district?"));
        questions.put(new QuestionKey(1, Category.DISTRICT, 400), new Question(1, Category.DISTRICT, 400, "Who directly represents the district to an individual Scouting unit?"));

        questions.put(new QuestionKey(1, Category.TRAINING, 100), new Question(1, Category.TRAINING, 100, "What is the first training any adult leader should receive in Scouting?"));
        questions.put(new QuestionKey(1, Category.TRAINING, 200), new Question(1, Category.TRAINING, 200, "Who is responsible for making sure that training occurs within a district?"));
        questions.put(new QuestionKey(1, Category.TRAINING, 300), new Question(1, Category.TRAINING, 300, "What is the only training award of the Scouting movement that is the same in every country where it is awarded?"));
        questions.put(new QuestionKey(1, Category.TRAINING, 400), new Question(1, Category.TRAINING, 400, "Name the award that a Scoutmaster can earn after three years as a Scoutmaster and meeting additional requirements."));

        questions.put(new QuestionKey(1, Category.LEADERSHIP, 100), new Question(1, Category.LEADERSHIP, 100, "What is the totem of Gilwell Park?"));
        questions.put(new QuestionKey(1, Category.LEADERSHIP, 200), new Question(1, Category.LEADERSHIP, 200, "Baden-Powell believed that Scouting was a game with what?"));
        questions.put(new QuestionKey(1, Category.LEADERSHIP, 300), new Question(1, Category.LEADERSHIP, 300, "What is the Mission Statement of the Boy Scouts of America?"));
        questions.put(new QuestionKey(1, Category.LEADERSHIP, 400), new Question(1, Category.LEADERSHIP, 400, "What are three characteristics of a vision?"));

        //round 2
        questions.put(new QuestionKey(2, Category.CUBS, 100), new Question(2, Category.CUBS, 100, "How many adventures must a boy complete to earn his Tiger rank?"));
        questions.put(new QuestionKey(2, Category.CUBS, 200), new Question(2, Category.CUBS, 200, "Who is Akela?"));
        questions.put(new QuestionKey(2, Category.CUBS, 300), new Question(2, Category.CUBS, 300, "What does \"Webelos\" mean?"));
        questions.put(new QuestionKey(2, Category.CUBS, 400), new Question(2, Category.CUBS, 400, "What colors are in the recognition knot for the Den Leader Training Award?"));

        questions.put(new QuestionKey(2, Category.SCOUTS, 100), new Question(2, Category.SCOUTS, 100, "Name the four uniform items that must be worn for a Scout to be in proper or complete uniform."));
        questions.put(new QuestionKey(2, Category.SCOUTS, 200), new Question(2, Category.SCOUTS, 200, "From whom should a Boy Scout receive his first troop and patrol leadership training?"));
        questions.put(new QuestionKey(2, Category.SCOUTS, 300), new Question(2, Category.SCOUTS, 300, "How many merit badges must an Eagle Scout earn to wear both a Silver Palm and Bronze Palm on his Eagle Scout Award?"));
        questions.put(new QuestionKey(2, Category.SCOUTS, 400), new Question(2, Category.SCOUTS, 400, "What do the two stars stand for on the First Class badge?"));

        questions.put(new QuestionKey(2, Category.VARSITY, 100), new Question(2, Category.VARSITY, 100, "What is the adult leader of a Varsity Scout team called?"));
        questions.put(new QuestionKey(2, Category.VARSITY, 200), new Question(2, Category.VARSITY, 200, "Which members of a Varsity Scout team participate in a team planning meeting?"));
        questions.put(new QuestionKey(2, Category.VARSITY, 300), new Question(2, Category.VARSITY, 300, "A Varsity Scout team is broken down into smaller groups of Varsity Scouts. What are these groups called?"));
        questions.put(new QuestionKey(2, Category.VARSITY, 400), new Question(2, Category.VARSITY, 400, "Name the two major youth awards that are unique to Varsity Scouting."));

        questions.put(new QuestionKey(2, Category.VENTURE, 100), new Question(2, Category.VENTURE, 100, "How many of the core requirements must be earned for the Ranger Award?"));
        questions.put(new QuestionKey(2, Category.VENTURE, 200), new Question(2, Category.VENTURE, 200, "Can a Venturer holding the rank of Second Class Scout earn his Eagle Scout Award while a member of a Venturing crew?"));
        questions.put(new QuestionKey(2, Category.VENTURE, 300), new Question(2, Category.VENTURE, 300, "What are the youth awards that are unique to Venturing?"));
        questions.put(new QuestionKey(2, Category.VENTURE, 400), new Question(2, Category.VENTURE, 400, "What are the four areas of emphasis in the Venturing program model?"));

        questions.put(new QuestionKey(2, Category.DISTRICT, 100), new Question(2, Category.DISTRICT, 100, "Is a chartered organization representative a voting member of his or her council?"));
        questions.put(new QuestionKey(2, Category.DISTRICT, 200), new Question(2, Category.DISTRICT, 200, "Who should coordinate a district wide pinewood derby runoff?"));
        questions.put(new QuestionKey(2, Category.DISTRICT, 300), new Question(2, Category.DISTRICT, 300, "If the district chairperson is absent and has not made other arrangements, who runs a district committee meeting?"));
        questions.put(new QuestionKey(2, Category.DISTRICT, 400), new Question(2, Category.DISTRICT, 400, "Who appoints a den chief?"));

        questions.put(new QuestionKey(2, Category.TRAINING, 100), new Question(2, Category.TRAINING, 100, "A professional Scouter (that is, someone employed by Scouting) may earn the Professional Training Award. What colors are in the recognition knot for this award?"));
        questions.put(new QuestionKey(2, Category.TRAINING, 200), new Question(2, Category.TRAINING, 200, "Within a Scouting unit, what person ensures that all adult leaders receive the appropriate training awards and recognitions?"));
        questions.put(new QuestionKey(2, Category.TRAINING, 300), new Question(2, Category.TRAINING, 300, "Is Wood Badge considered advanced leadership training?"));
        questions.put(new QuestionKey(2, Category.TRAINING, 400), new Question(2, Category.TRAINING, 400, "What training awards are required for a Scouter to be eligible for the Silver Beaver Award?"));

        questions.put(new QuestionKey(2, Category.LEADERSHIP, 100), new Question(2, Category.LEADERSHIP, 100, "The kudu is an animal native to what continent?"));
        questions.put(new QuestionKey(2, Category.LEADERSHIP, 200), new Question(2, Category.LEADERSHIP, 200, "The Wood Badge presentation on \"Listening to Learn\" discussed two kinds of effective listening. One was active listening.  What was the other?"));
        questions.put(new QuestionKey(2, Category.LEADERSHIP, 300), new Question(2, Category.LEADERSHIP, 300, "The Wood Badge presentation on “Communication” noted that Aristotle broke down communication into what three parts?"));
        questions.put(new QuestionKey(2, Category.LEADERSHIP, 400), new Question(2, Category.LEADERSHIP, 400, "What are the four S’s of a successful campfire program?"));

        //round 3
        questions.put(new QuestionKey(3, Category.CUBS, 100), new Question(3, Category.CUBS, 100, "What is the name of the BSA book that contains successful ideas to add sparkle to den and pack activities?"));
        questions.put(new QuestionKey(3, Category.CUBS, 200), new Question(3, Category.CUBS, 200, "Regardless of his age, what is the first rank a Cub Scout earns when he joins the Cub Scout program??"));
        questions.put(new QuestionKey(3, Category.CUBS, 300), new Question(3, Category.CUBS, 300, "Four awards that a Cub Scout or a den can earn include a requirement for outdoor activity or service. Name two of these awards."));
        questions.put(new QuestionKey(3, Category.CUBS, 400), new Question(3, Category.CUBS, 400, "Who appears as a mentor in each of the Cub Scout youth handbooks?"));

        questions.put(new QuestionKey(3, Category.SCOUTS, 100), new Question(3, Category.SCOUTS, 100, "On the Boy Scout length-of-service pin, what color is the plastic backing?"));
        questions.put(new QuestionKey(3, Category.SCOUTS, 200), new Question(3, Category.SCOUTS, 200, "According to the Boy Scout Handbook, what factor determines whether the members of a troop wear their neckerchiefs over the collars of their shirts, or under their collars?"));
        questions.put(new QuestionKey(3, Category.SCOUTS, 300), new Question(3, Category.SCOUTS, 300, "When can a Boy Scout in complete uniform wear his Order of the Arrow sash and merit badge sash simultaneously?"));
        questions.put(new QuestionKey(3, Category.SCOUTS, 400), new Question(3, Category.SCOUTS, 400, "Patrol members wearing a small star segment beneath their patrol emblems have earned what award?"));

        questions.put(new QuestionKey(3, Category.VARSITY, 100), new Question(3, Category.VARSITY, 100, "Can a Varsity Scout be a member of the Order of the Arrow?"));
        questions.put(new QuestionKey(3, Category.VARSITY, 200), new Question(3, Category.VARSITY, 200, "Can a boy who is a Varsity Scout be registered in a Boy Scout troop at the same time?"));
        questions.put(new QuestionKey(3, Category.VARSITY, 300), new Question(3, Category.VARSITY, 300, "On the Varsity Scout length-of-service pin, what color is the plastic backing?"));
        questions.put(new QuestionKey(3, Category.VARSITY, 400), new Question(3, Category.VARSITY, 400, "Can Varsity Scouts participate in co-ed activities?"));

        questions.put(new QuestionKey(3, Category.VENTURE, 100), new Question(3, Category.VENTURE, 100, "What kind of service project is required for a Venturer to become a Ranger?"));
        questions.put(new QuestionKey(3, Category.VENTURE, 200), new Question(3, Category.VENTURE, 200, "Which Venturing award encourages Venturers to adopt a healthy and active lifestyle?"));
        questions.put(new QuestionKey(3, Category.VENTURE, 300), new Question(3, Category.VENTURE, 300, "Who nominates the Venturing Advisor for the Unit Leader Award of Merit?"));
        questions.put(new QuestionKey(3, Category.VENTURE, 400), new Question(3, Category.VENTURE, 400, "What award can be presented by councils, areas, regions, or the BSA National Council to Venturers who have made exceptional contributions to Venturing?"));

        questions.put(new QuestionKey(3, Category.DISTRICT, 100), new Question(3, Category.DISTRICT, 100, "Can members of a patrol have a different logo or design on their neckerchiefs as long as the neckerchiefs are the same color as those of the rest of the troop?"));
        questions.put(new QuestionKey(3, Category.DISTRICT, 200), new Question(3, Category.DISTRICT, 200, "How many medals (not recognition knots) may be worn on the official uniform at any one time?"));
        questions.put(new QuestionKey(3, Category.DISTRICT, 300), new Question(3, Category.DISTRICT, 300, "What award can a Scouter receive for a donation of $1,000 to the BSA through his/her council?"));
        questions.put(new QuestionKey(3, Category.DISTRICT, 400), new Question(3, Category.DISTRICT, 400, "At what level of Scouting is the District Award of Merit presented?"));

        questions.put(new QuestionKey(3, Category.TRAINING, 100), new Question(3, Category.TRAINING, 100, "Describe the shoes that are to be worn with the official uniform."));
        questions.put(new QuestionKey(3, Category.TRAINING, 200), new Question(3, Category.TRAINING, 200, "In a chart of troop leadership positions, to whom do the troop scribe and librarian report?"));
        questions.put(new QuestionKey(3, Category.TRAINING, 300), new Question(3, Category.TRAINING, 300, "To become a junior assistant Scoutmaster, how old must a Scout be?"));
        questions.put(new QuestionKey(3, Category.TRAINING, 400), new Question(3, Category.TRAINING, 400, "What do the initials S. S. stand for in the name Robert S. S. Baden-Powell?"));

        questions.put(new QuestionKey(3, Category.LEADERSHIP, 100), new Question(3, Category.LEADERSHIP, 100, "How many goals are there on a Wood Badge ticket?"));
        questions.put(new QuestionKey(3, Category.LEADERSHIP, 200), new Question(3, Category.LEADERSHIP, 200, "A team’s stage of development can be determined by measuring what two aspects of that team?"));
        questions.put(new QuestionKey(3, Category.LEADERSHIP, 300), new Question(3, Category.LEADERSHIP, 300, "What are the four stages of team development?"));
        questions.put(new QuestionKey(3, Category.LEADERSHIP, 400), new Question(3, Category.LEADERSHIP, 400, "What are the BSA’s four steps to advancement?"));
    }

    @PostConstruct
    public void init() {
        Map<QuestionKey, Question> questions = this.repository.getGameBoard();
        if (questions != null && !questions.isEmpty()) {
            this.questions = questions;
        }

        Map<Critter, Integer> score = this.repository.getScore();
        if (score != null && !score.isEmpty()) {
            this.score = score;
        }
    }

    public Question getQuestion(int round, Category category, int points) {
        return questions.get(new QuestionKey(round, category, points));
    }

    public void answerQuestion(int round, Category category, int points) {
        Question question = this.getQuestion(round, category, points);
        question.setAnswered(true);
        repository.storeGameBoard(this.questions);
    }

    public void correctScore(Critter critter, int points) {
        int totalPoints = score.get(critter);
        totalPoints += points;
        score.put(critter, totalPoints);
        repository.storeScore(this.score);
    }

    public void incorrectScore(Critter critter, int points) {
        int totalPoints = score.get(critter);
        totalPoints -= points;
        score.put(critter, totalPoints);
        repository.storeScore(this.score);
    }

    public void finalQuestion(Critter critter, int points, boolean correct) {
        int totalPoints = score.get(critter);
        if (correct) {
            totalPoints += points;
        } else {
            totalPoints -= points;
        }
        score.put(critter, totalPoints);
        repository.storeScore(score);
    }

    public Map<Category, Collection<Question>> getQuestions(int round) {
        Multimap<Category, Question> roundMap = TreeMultimap.create(
                (Category c1, Category c2) -> c1.compareTo(c2),
                (Question q1, Question q2) -> q1.compareTo(q2));
        Map<QuestionKey, Question> filteredQuestions = questions.entrySet().stream()
                .filter(e -> e.getKey().getRound() == round)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        filteredQuestions.forEach((k,v) -> roundMap.put(k.getCategory(), v));
        return roundMap.asMap();
    }

    public Map<Critter, Integer> getScore() {
        return this.score;
    }

}
