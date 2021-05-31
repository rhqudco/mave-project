package com.maveProject.mave;

import com.maveProject.mave.domain.*;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * 임시로 DB에 시작 데이터 넣음
 */

@Profile("local")
@RequiredArgsConstructor
@Component
public class InitDb {

    private final InitService initService;

    @PostConstruct
    public void init(){
        initService.dbInit();
    }


    @Transactional
    @RequiredArgsConstructor
    @Component
    static class InitService{

        private final EntityManager em;

        public void dbInit(){
            QuestionBank questionBank = new QuestionBank();
            questionBank.setQuestionNumber(1l);
            questionBank.setContent("첫 번째 질문입니다.");

            em.persist(questionBank);

            Member member = new Member("hello1");
            em.persist(member);

            Group group = new Group("그루비룸", LocalDateTime.now(), IsFinish.NO, Flower.ZERO, 1);
            em.persist(group);
        }

    }
}
