package org.hanna.woodbadge.gameshow;

import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.KeyDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.type.TypeFactory;
import org.hanna.woodbadge.gameshow.model.Critter;
import org.hanna.woodbadge.gameshow.model.Question;
import org.hanna.woodbadge.gameshow.model.QuestionKey;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Created by timhanna on 8/14/16.
 */
@Repository
public class GameShowRepository {

    private ObjectMapper objectMapper = new ObjectMapper();
    private static final Logger LOGGER = Logger.getLogger(GameShowRepository.class.toString());

    @PostConstruct
    public void init() {
        SimpleModule module = new SimpleModule();
        module.addKeyDeserializer(QuestionKey.class, new KeyDeserializer() {
            @Override
            public Object deserializeKey(String key, DeserializationContext ctxt) throws IOException {
                return new QuestionKey(key);
            }
        });
        objectMapper.registerModule(module);
    }

    public void storeGameBoard(Map<QuestionKey, Question> gameBoard) {
        try {
            objectMapper.writeValue(new File("questions.json"), gameBoard);
        } catch (IOException e) {
            LOGGER.info("question.json not found.");
        }
    }

    public Map<QuestionKey, Question> getGameBoard() {
        Map<QuestionKey, Question> gameBoard = null;
        try {
            gameBoard = objectMapper.readValue(new File("questions.json"), TypeFactory.defaultInstance()
                    .constructMapType(HashMap.class, QuestionKey.class, Question.class));
        } catch (IOException e) {
            LOGGER.info("question.json not found.");
        }
        return gameBoard;
    }

    public void storeScore(Map<Critter, Integer> score) {
        try {
            objectMapper.writeValue(new File("score.json"), score);
        } catch (IOException e) {
            LOGGER.info("score.json not found.");
        }
    }

    public Map<Critter, Integer> getScore() {
        Map<Critter, Integer> score = null;
        try {
            score = objectMapper.readValue(new File("score.json"), TypeFactory.defaultInstance()
                    .constructMapType(HashMap.class, Critter.class, Integer.class));
        } catch (IOException e) {
            LOGGER.info("score.json not found.");
        }
        return score;
    }

}
