package ua.com.kerilka.job_exchange.service;


import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


@Service
public class AboutUsService {

    private final Path filePath = Paths.get("about.txt");

    public String getAboutText() {
        if (!Files.exists(filePath)) {
            // Дефолтний текст, якщо файл ще не створено
            return "Ми — молода команда, що прагне спростити процес взаємодії між роботодавцями та шукачами роботи. Наша мета — створити прозорий та ефективний ринок праці.";
        }
        try {
            return Files.readString(filePath);
        } catch (IOException e) {
            return "Помилка читання файлу опису.";
        }
    }

    // Метод для запису тексту
    public void saveAboutText(String newText) {
        try {
            Files.writeString(filePath, newText);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
