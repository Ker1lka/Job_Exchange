package ua.com.kerilka.job_exchange.bl;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ua.com.kerilka.job_exchange.entity.Vacancy;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FavoriteItem {
    private Vacancy vacancy;
    private int priority;
}
