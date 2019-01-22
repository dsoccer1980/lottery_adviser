package ru.dsoccer1980.lottery_adviser.model;

import lombok.*;
import org.hibernate.validator.constraints.Range;
import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "NUMBERS")
@ToString
@NoArgsConstructor
public class Numbers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    @Getter @Setter
    private int id;

    @NotNull
    @Range(min = 1)
    @Column(name = "DRAWNUMBER")
    @Getter @Setter
    private int drawNumber;

    @NotNull
    @Range(min = 1, max = 6)
    @Column(name = "INDEX")
    @Getter @Setter
    private int index;

    @NotNull
    @Range(min = 1, max = 48)
    @Column(name = "NUMBER")
    @Getter @Setter
    private int number;

    public Numbers(int drawNumber, int index, int number) {
        this.drawNumber = drawNumber;
        this.index = index;
        this.number = number;
    }
}