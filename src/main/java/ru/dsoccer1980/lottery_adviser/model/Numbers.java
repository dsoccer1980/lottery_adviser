package ru.dsoccer1980.lottery_adviser.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Size;


@Entity
@Table(name = "NUMBERS")
@ToString
public class Numbers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DRAWNUMBER")
    @Getter @Setter
    private int drawNumber;

    @NotEmpty
    @Size(min=1, max = 48)
    @Column(name = "NUMBER1")
    @Getter @Setter
    private int number1;

    @NotEmpty
    @Size(min=1, max = 48)
    @Column(name = "NUMBER2")
    @Getter @Setter
    private int number2;

    @NotEmpty
    @Size(min=1, max = 48)
    @Column(name = "NUMBER3")
    @Getter @Setter
    private int number3;

    @NotEmpty
    @Size(min=1, max = 48)
    @Column(name = "NUMBER4")
    @Getter @Setter
    private int number4;

    @NotEmpty
    @Size(min=1, max = 48)
    @Column(name = "NUMBER5")
    @Getter @Setter
    private int number5;

    @NotEmpty
    @Size(min=1, max = 48)
    @Column(name = "NUMBER6")
    @Getter @Setter
    private int number6;

    public Numbers() {
    }
}