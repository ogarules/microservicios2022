package com.example.demo.crosscutting;

import com.example.demo.models.Card;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
@Configuration
public class CardMaskerAspect {
    
    @Around("execution(com.example.demo.models.Card com.example.demo.services.*.*(..))")
    public Object maskCard(ProceedingJoinPoint pjp) throws Throwable {

        log.info("Masking card number...");

        Object result = pjp.proceed();

        Card cardItem = (Card) result;
        String number = cardItem.getCardNumber();
        cardItem.setCardNumber("XXXX-XXXX-XXXX-XX" + number.substring(number.length() - 2, number.length()));

        log.info("Masked card {} to {}", number, cardItem.getCardNumber());

        return cardItem;
    }

    @Around("execution(Iterable<com.example.demo.models.Card> com.example.demo.services.*.*(..))")
    public Object maskCards(ProceedingJoinPoint pjp) throws Throwable {

        log.info("Masking card number...");

        Object result = pjp.proceed();

        Iterable<Card> cardItems = (Iterable<Card>) result;

        for (Card cardItem : cardItems) {
            String number = cardItem.getCardNumber();
            cardItem.setCardNumber("XXXX-XXXX-XXXX-XX" + number.substring(number.length() - 2, number.length()));

            log.info("Masked card {} to {}", number, cardItem.getCardNumber());    
        }       

        return cardItems;
    }
}
