package com.and_backend.home.quest;

import com.and_backend.enums.CopeWay;
import com.and_backend.enums.LossSubject;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;


@Entity @Table(name="quest_bank")
@Data
public class QuestBank {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questBankId;

    private String text;
    @Enumerated(EnumType.STRING)
    private LossSubject subject;
    @Enumerated(EnumType.STRING)
    private CopeWay copeWay;

    @OneToMany(mappedBy = "questBank")
    private List<Quest> quests = new ArrayList<>();

}
