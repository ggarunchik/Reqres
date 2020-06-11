package tests;

import adapters.hh.HHVacancyAdapter;
import org.testng.annotations.Test;

public class HeadHunterAPITest extends BaseTest {

    @Test
    public void getVacancies() {
        models.hh.Vacancies vacancies = new HHVacancyAdapter().get("QA");
        System.out.println(vacancies);
        for (models.hh.Vacancy vacancy : vacancies.getItems()) {
            System.out.println("_______________________________________");
            System.out.println("Вакансия: " + vacancy.getName());
            if (vacancy.getSalary() != null) {
                System.out.println("Начальная ЗП: " + vacancy.getSalary().getFrom() + " " + vacancy.getSalary().getCurrency());
            }
        }
    }

}
