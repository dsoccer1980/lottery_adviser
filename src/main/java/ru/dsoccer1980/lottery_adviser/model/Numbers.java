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
    @Range(min=1)
    @Column(name = "DRAWNUMBER")
    @Getter @Setter
    private int drawNumber;

    @NotNull
    @Range(min=1, max=48)
    @Column(name = "NUMBER1")
    @Getter @Setter
    private int number1;

    @NotNull
    @Range(min=1, max=48)
    @Column(name = "NUMBER2")
    @Getter @Setter
    private int number2;

    @NotNull
    @Range(min=1, max=48)
    @Column(name = "NUMBER3")
    @Getter @Setter
    private int number3;

    @NotNull
    @Range(min=1, max=48)
    @Column(name = "NUMBER4")
    @Getter @Setter
    private int number4;

    @NotNull
    @Range(min=1, max=48)
    @Column(name = "NUMBER5")
    @Getter @Setter
    private int number5;

    @NotNull
    @Range(min=1, max=48)
    @Column(name = "NUMBER6")
    @Getter @Setter
    private int number6;

    public Numbers(int drawNumber, int number1, int number2, int number3, int number4, int number5, int number6) {
        this.drawNumber = drawNumber;
        this.number1 = number1;
        this.number2 = number2;
        this.number3 = number3;
        this.number4 = number4;
        this.number5 = number5;
        this.number6 = number6;
    }
}