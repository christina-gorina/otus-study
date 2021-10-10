package com.christinagorina.homework;

import com.christinagorina.homework.domain.Food;
import com.christinagorina.homework.domain.OrderItem;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.scheduling.PollerMetadata;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;

@IntegrationComponentScan
@SuppressWarnings({"resource", "Duplicates", "InfiniteLoopStatement"})
@ComponentScan
@Configuration
@EnableIntegration
public class Application {
    private static final String[] MENU = {"coffee", "tea", "smoothie", "whiskey", "beer", "cola", "water"};

    @Bean
    public QueueChannel itemsChannel() {
        return MessageChannels.queue(10).get();
    }

    @Bean
    public PublishSubscribeChannel foodChannel() {
        return MessageChannels.publishSubscribe().get();
    }

    @Bean(name = PollerMetadata.DEFAULT_POLLER)
    public PollerMetadata poller() {
        return Pollers.fixedRate(100).maxMessagesPerPoll(2).get();
    }

    @Bean
    public IntegrationFlow cafeFlow() {
        return IntegrationFlows.from("itemsChannel")
                .split()
                .<OrderItem, Boolean>route(OrderItem::getForBar,
                    mapping -> mapping
                        .subFlowMapping(true, subflow -> subflow
                            .handle("kitchenService", "cook")
                        )
                        .subFlowMapping(false, subflow -> subflow
                            .handle("barService", "cook")
                        )
                )
                .<Food, Food>transform(p -> {
                    if(p.getStock()){
                        p.setName("DoublePortion of " + p.getName());
                    }
                    return p;
                })
                .aggregate()
                .channel("foodChannel")
                .get();

    }

    public static void main(String[] args) throws Exception {
        AbstractApplicationContext ctx = new AnnotationConfigApplicationContext(Application.class);

        Cafe cafe = ctx.getBean(Cafe.class);

        ForkJoinPool pool = ForkJoinPool.commonPool();

        while (true) {
            Thread.sleep(7000);

            pool.execute(() -> {
                Collection<OrderItem> items = generateOrderItems();
                System.out.println("New orderItems: " +
                        items.stream().map(OrderItem::getItemName)
                                .collect(Collectors.joining(",")));
                Collection<Food> food = cafe.process(items);
                System.out.println("Ready food: " + food.stream()
                        .map(Food::getName)
                        .collect(Collectors.joining(",")));
            });
        }
    }

    private static OrderItem generateOrderItem() {
        return new OrderItem(MENU[RandomUtils.nextInt(0, MENU.length)], RandomUtils.nextBoolean());
    }

    private static Collection<OrderItem> generateOrderItems() {
        List<OrderItem> items = new ArrayList<>();
        for (int i = 0; i < RandomUtils.nextInt(1, 5); ++i) {
            items.add(generateOrderItem());
        }
        return items;
    }
}