package ua.com.kerilka.job_exchange.bl;

import lombok.Getter;
import lombok.Setter;
import ua.com.kerilka.job_exchange.entity.Vacancy;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Favorites {
    private List<FavoriteItem> favoriteList;
    private int sumEl;

    public Favorites() {
        this.favoriteList = new ArrayList<>();
        this.sumEl = 0;
    }

    public synchronized void addVacancyToFavorites(Vacancy vacancy, int priority) {
        for (FavoriteItem item : favoriteList) {
            if(item.getVacancy().getId().equals(vacancy.getId())) {
                item.setPriority(priority);
                return;
            }
        }
        favoriteList.add(new FavoriteItem(vacancy, priority));
    }
    public synchronized void removeVacancyFromFavorites(Vacancy vacancy) {
        for (FavoriteItem item : favoriteList) {
            if(item.getVacancy().getId().equals(vacancy.getId())) {
                favoriteList.remove(item);
                break;
            }
        }
    }
    public synchronized int getSumItem() {
        return sumEl = favoriteList.size();
    }

}
