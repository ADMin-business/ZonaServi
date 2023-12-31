package GrupoF.Proyecto3.Servicios;

import GrupoF.Proyecto3.Entidades.Imagen;
import GrupoF.Proyecto3.Repositorios.ImagenRepositorio;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImagenServicio {

    @Autowired
    private ImagenRepositorio iR;

    @Transactional
    public Imagen guardar(MultipartFile archivo) throws Exception {

        try {

            Imagen imagen = new Imagen();

            imagen.setMime(archivo.getContentType());

            imagen.setNombre(archivo.getName());

            imagen.setContenido(archivo.getBytes());

            System.out.print(imagen.getNombre());

            return iR.save(imagen);

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

        return null;
    }

    @Transactional
    public Imagen actualizar(MultipartFile archivo, String idImagen) throws Exception {

        try {

            Imagen imagen = new Imagen();

            if (idImagen != null) {
                Optional<Imagen> respuesta = iR.findById(idImagen);

                if (respuesta.isPresent()) {
                    imagen = respuesta.get();
                }
            }

            imagen.setMime(archivo.getContentType());

            imagen.setNombre(archivo.getName());

            imagen.setContenido(archivo.getBytes());

            return iR.save(imagen);

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        return null;

    }

    @Transactional(readOnly = true)
    public List<Imagen> listarTodos() {
        return iR.findAll();
    }

}
