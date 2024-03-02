package com.example.backendphotobook.services;

import com.example.backendphotobook.entities.AlbunsEntity;
import com.example.backendphotobook.entities.ImagensAlbunsEntity;
import com.example.backendphotobook.repository.ImagensAlbunsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
public class ImagensAlbunsService {

    @Autowired
    private ImagensAlbunsRepository imagensAlbunsRepository;

    public List<String> findByAlbumId(AlbunsEntity albunsEntity) {

        List<String> imagensList = new ArrayList<>();
        Optional<List<ImagensAlbunsEntity>> imagensAlbunsEntityList = imagensAlbunsRepository.findByAlbumId(albunsEntity);

        if (imagensAlbunsEntityList.isPresent()) {

            for (ImagensAlbunsEntity imagensAlbunsEntity : imagensAlbunsEntityList.get()) {
                String imagem = Base64.getEncoder().encodeToString(imagensAlbunsEntity.getImagem());
                imagensList.add(imagem);
            }

            return imagensList;
        }

        return new ArrayList<>();
    }

    public void save(ImagensAlbunsEntity imagensAlbunsEntity) {
        imagensAlbunsRepository.save(imagensAlbunsEntity);
    }
}
