package ru.fit.app.models;

import ru.fit.app.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
/ Имитация базы данных
 */

public class DataBase {
    private static DataBase instance = null;
    private List<Workout> workouts;

    private DataBase() {
        init();
    }

    private void init(){
        // init exercise one by one
        Exercise exercise0 = new Exercise("Жим Лежа","Это базовое упражнение с поднятием тяжести, выполняемое на скамье, является ключевым для получения мощной и широкой грудной клетки", R.drawable.bench_press);
        exercise0.setDescription("Исходное положение:\n" +
                "\n" +
                "Лежа на горизонтальной скамье.\n" +
                "\n" +
                "Выполнение:\n" +
                "\n" +
                "штангу снимают с крепления обеими руками;\n" +
                "снаряд опускают к середине груди до легкого касания тела;\n" +
                "выжимают штангу, не выдыхая, пока локтевые суставы полностью зафиксируются.\n" +
                "Ноги должны стоять на полу, ягодицы быть плотно прижатыми к поверхности скамьи, лопатки — сведенными, а грудная клетка — выставленной вперед.\n" +
                "\n" +
                "Особенностью жима, в отличие от приседания и становой тяги, является возможность поднимать более тяжелый вес при минимальном риске повреждения плечевого сустава. Это достигается за счет того, что штанга поднимается от груди к плечам по диагонали, а сама траектория движения снаряда имеет небольшой угол относительно вертикали.");
        Exercise exercise1 = new Exercise("Свидение рук в тренажере","Сведение рук в тренажере «бабочка» призвано изолировать пекторальные мышцы",R.drawable.bringing_hands_together);
        exercise1.setDescription("Настройка тренажера:\n" +
                "\n" +
                "Сиденье нужно установить так, чтобы спортсмен мог сводить руки перед грудью, выражаясь анатомически верно, приводить плечо к средней линии тела. Его предплечья должны быть в одной плоскости с проекцией плечевого сустава;\n" +
                "Спинка должна быть горизонтальна, наклон нужно убрать, если он есть;\n" +
                "Если нет возможности прижаться к спинке тренажера из-за длины рук, стоит взяться не за рукоятки, а чуть выше или ниже, но лопатки касаются спинки в любом случае;\n" +
                "Стартовое положение ручек – анатомическая нейтраль, то есть то положение, откуда мы стартуем с разведенными в сторону руками, параллельными полу;\n" +
                "Если есть стаж в бодибилдинге, нормально работают суставы, и нет проблем со спиной, можно убрать ручки чуть назад так, чтобы в негативной фазе чувствовалось большее растяжение мышц. Новичкам этот вариант не рекомендуется;\n" +
                "Высота сиденья подбирается так, чтобы стопы касались пола, и был хороший упор ногами. Если его нет, стоит подложить под стопы блоки или блины\n" +
                "Исходное положение – нужно сесть на сиденье тренажера, упереться ногами в пол, и взять ручки тренажера свободным хватом. Далее плечи отводятся от ушей, лопатки прижимаются к позвоночнику, и опускаются к тазу. Прогиб поясницы нужно сохранить естественным.\n" +
                "\n" +
                "Движение:\n" +
                "\n" +
                "Рукоятки тренажера сводим к центру тела, за счет сокращения мышц груди;\n" +
                "Смещение хвата вверх переносит акцент к верхнему пучку пекторальных мышц, опускание – к низу груди, но акценты незначительны;\n" +
                "Разведение производится мягко, руки отводятся в стороны плавно, без рывков;\n" +
                "Движение лучше сводить и разводить на два счета, не делая рывков;\n" +
                "Все повторения выполняются без пауз, единым подходом, отдых между сетами 1-2 минуты");
        Exercise exercise2 = new Exercise("Тяга штанги в наклоне","Какое-то описание",R.drawable.rod_pull_in_tilt);
        Exercise exercise3 = new Exercise("Подтягивания","Какое-то описание",R.drawable.pull_ups);
        Exercise exercise4 = new Exercise("Приседания","Какое-то описание",R.drawable.squats);
        Exercise exercise5 = new Exercise("Становая тяга","Какое-то описание",R.drawable.deadlift);
        Exercise exercise6 = new Exercise("Молоток","Какое-то описание",R.drawable.hammer);
        Exercise exercise7 = new Exercise("Отжимания на брусьях","Какое-то описание",R.drawable.push_ups);
        Exercise exercise8 = new Exercise("Скручивания","Какое-то описание",R.drawable.twisting);
        Exercise exercise9 = new Exercise("Планка","Какое-то описание",R.drawable.plank_exercise);

        Workout workout0 = new Workout("План для новичков", "Когда впервые решил заняться спортом, это для тебя");
        workout0.addExercise(exercise1);
        workout0.addExercise(exercise3);
        workout0.addExercise(exercise4);
        workout0.addExercise(exercise8);
        workout0.setChoose(true);
        Workout workout1 = new Workout("Любителький подход", "Если уже не первый день в зале, то это для тебя");
        workout1.addExercise(exercise0);
        workout1.addExercise(exercise2);
        workout1.addExercise(exercise3);
        workout1.addExercise(exercise4);
        workout1.addExercise(exercise7);
        workout1.addExercise(exercise8);
        Workout workout2 = new Workout("Проффисиональный план", "Просто не будет");
        workout2.addExercise(exercise0);
        workout2.addExercise(exercise1);
        workout2.addExercise(exercise2);
        workout2.addExercise(exercise3);
        workout2.addExercise(exercise4);
        workout2.addExercise(exercise5);
        workout2.addExercise(exercise6);
        workout2.addExercise(exercise7);
        workout2.addExercise(exercise8);
        workout2.addExercise(exercise9);
        Workout workout3 = new Workout("Для самых юных", "Дети тоже хотят заниматься спортом");
        workout3.addExercise(exercise3);
        workout3.addExercise(exercise4);
        workout3.addExercise(exercise7);
        workout3.addExercise(exercise8);
        workout3.addExercise(exercise9);
        Workout workout4 = new Workout("За пятьдесят", "Все получиться с этим планом");
        workout4.addExercise(exercise1);
        workout4.addExercise(exercise4);
        workout4.addExercise(exercise5);
        workout4.addExercise(exercise6);
        workout4.addExercise(exercise8);
        workout4.addExercise(exercise9);

        workouts = new ArrayList<>(Arrays.asList(workout0,workout1,workout2,workout3,workout4));
    }

    public List<Workout> getWorkouts() {
        return workouts;
    }

    public Workout getChosenWorkout(){
        for(Workout workout : workouts){
            if(workout.isChoose()){
                return workout;
            }
        }
        return null;
    }

    public static DataBase getInstance(){
        if(instance == null){
            instance = new DataBase();
        }
        return instance;
    }
}
