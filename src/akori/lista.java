/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package akori;

import static akori.DrawLevel.namefile;
import java.io.IOException;

/**
 *
 * @author Claudio
 */
public class lista {
    public static void main(String[] args) throws IOException {
        String[] URLlist = {"http://www.mbauchile.cl/",
            "http://www.mbauchile.cl/plan-de-estudios/",
            "http://www.mbauchile.cl/financiamiento-y-becas/",
            "http://www.mbauchile.cl/descargar-folleto/",
            "http://www.mbauchile.cl/tipo/jornada-completa-y-media-jornada",
            "http://www.mbauchile.cl/2013/06/13/mba-uchile-lider-en-ranking-latinoamericano/",
            "http://www.mbauchile.cl/plataforma-laboral/",
            "http://www.mbauchile.cl/servicio-medico/",
            "http://www.mbauchile.cl/contenido-cursos/",
            "http://www.mbauchile.cl/tipo/jornada-parcial/",
            "http://www.mbauchile.cl/todos/noticias/page/2/",
            "http://www.mbauchile.cl/invitados/",
            "http://www.mbauchile.cl/mapa-ubicacion/",
            "http://www.mbauchile.cl/academicos/andres-weintraub/",
            "http://www.mbauchile.cl/testimonios/felipe-sepulveda/",
            "http://www.mbauchile.cl/ipad/",
            "http://www.mbauchile.cl/postulacion-online/",
            "http://www.mbauchile.cl/admision/",
            "http://www.mbauchile.cl/doble-grado/",
            "http://www.mbauchile.cl/caracteristicas/",
            "http://www.mbauchile.cl/contacto/",
            "http://www.mbauchile.cl/generaciones/",
            "http://www.mbauchile.cl/perfil-alumnos/",
            "http://www.mbauchile.cl/porque-elegirnos/",
            "http://www.mbauchile.cl/formularios/",
            "http://www.mbauchile.cl/reglamento/",
            "http://www.mbauchile.cl/propuesta-academica/",
            "http://www.mbauchile.cl/u-cursos/",
            "http://www.mbauchile.cl/egresados/",
            "http://www.mbauchile.cl/corporacion-egresados/",
            "http://www.mbauchile.cl/quienes-participan/",
            "http://www.mbauchile.cl/biblioteca/",
            "http://www.mbauchile.cl/metodologia/"
        };
        
        System.out.println("lista = {");
        for (int k = 0; k < URLlist.length; ++k) {

            String URL = URLlist[k];
            String NAME = namefile(URL);
            
            System.out.println("'"+NAME+".txt', ...");
        
        }
        System.out.println("};");
    }
}
