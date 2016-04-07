package com.mx.antorcha.BaseDatos;

import android.app.Activity;
import android.content.Context;

import com.mx.antorcha.Modelos.Deporte;
import com.mx.antorcha.Modelos.Disciplina;

import org.json.JSONArray;
import org.json.JSONException;

/**
 * Created by root on 6/04/16.
 */
public class CargarDeportesDisciplinas {

    static String DISCIPLINAS_JSON = "[{\"id\":\"1\",\"created_at\":\"2016-01-02 18:59:26\",\"updated_at\":\"0000-00-00 00:00:00\",\"descripcion\":\"Descripcion de disicplina\",\"estado\":\"0\",\"imagen\":\"principal\",\"nombre\":\"Acu\\u00e1ticos\"},{\"id\":\"2\",\"created_at\":\"2016-01-12 21:30:02\",\"updated_at\":\"2016-01-12 21:30:02\",\"descripcion\":\"danza.jpeg\",\"estado\":\"0\",\"imagen\":null,\"nombre\":\"A\\u00e9rea\"},{\"id\":\"3\",\"created_at\":\"2016-01-14 02:36:08\",\"updated_at\":\"2016-01-14 02:36:08\",\"descripcion\":\"\",\"estado\":\"0\",\"imagen\":null,\"nombre\":\"Agarre\"},{\"id\":\"4\",\"created_at\":\"2016-01-28 23:22:17\",\"updated_at\":\"2016-01-28 23:22:17\",\"descripcion\":\"orem ipsum dolor sit amet, consectetur adipiscing elit. Praesent lacinia placerat arcu, at imperdiet justo iaculis eu. Aliquam sit amet venenatis eros. Nulla et tincidunt nunc. Mauris luctus massa qui\",\"estado\":\"0\",\"imagen\":\"disciplina_Gimnasia_1454023336.png\",\"nombre\":\"Animales\"},{\"id\":\"7\",\"created_at\":\"2016-01-28 23:59:46\",\"updated_at\":\"2016-01-28 23:59:46\",\"descripcion\":\"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean imperdiet elit erat, quis egestas quam gravida at. Vivamus felis lacus, volutpat vehicula viverra quis, sodales sit amet ante. Morbi ves\",\"estado\":\"0\",\"imagen\":\"disciplina_Lorem ipsum _1454025585.png\",\"nombre\":\"Atletismo\"},{\"id\":\"8\",\"created_at\":\"2016-01-29 16:08:22\",\"updated_at\":\"2016-01-29 16:08:22\",\"descripcion\":\"Algun texto de prueba\",\"estado\":\"0\",\"imagen\":\"disciplina_Disciplina de prueba admin_1454083701.png\",\"nombre\":\"Motor\"},{\"id\":\"9\",\"created_at\":\"2016-02-28 00:27:03\",\"updated_at\":\"0000-00-00 00:00:00\",\"descripcion\":\"\",\"estado\":\"0\",\"imagen\":null,\"nombre\":\"Ciclismo\"},{\"id\":\"10\",\"created_at\":\"2016-02-28 00:27:57\",\"updated_at\":\"0000-00-00 00:00:00\",\"descripcion\":\"\",\"estado\":\"0\",\"imagen\":null,\"nombre\":\"Combate\"},{\"id\":\"11\",\"created_at\":\"2016-02-28 00:28:04\",\"updated_at\":\"0000-00-00 00:00:00\",\"descripcion\":\"\",\"estado\":\"0\",\"imagen\":null,\"nombre\":\"Entretenimiento\"},{\"id\":\"12\",\"created_at\":\"2016-02-28 00:28:09\",\"updated_at\":\"0000-00-00 00:00:00\",\"descripcion\":\"\",\"estado\":\"0\",\"imagen\":null,\"nombre\":\"Equipo\"},{\"id\":\"13\",\"created_at\":\"2016-02-28 00:28:11\",\"updated_at\":\"0000-00-00 00:00:00\",\"descripcion\":\"\",\"estado\":\"0\",\"imagen\":null,\"nombre\":\"Fuerza\"},{\"id\":\"14\",\"created_at\":\"2016-02-28 00:28:16\",\"updated_at\":\"0000-00-00 00:00:00\",\"descripcion\":\"\",\"estado\":\"0\",\"imagen\":null,\"nombre\":\"Fuerza mayor\"},{\"id\":\"15\",\"created_at\":\"2016-02-28 00:28:24\",\"updated_at\":\"0000-00-00 00:00:00\",\"descripcion\":\"\",\"estado\":\"0\",\"imagen\":null,\"nombre\":\"Miscel\\u00e1neas\"},{\"id\":\"16\",\"created_at\":\"2016-02-28 00:28:30\",\"updated_at\":\"0000-00-00 00:00:00\",\"descripcion\":\"\",\"estado\":\"0\",\"imagen\":null,\"nombre\":\"M\\u00faltiples\"},{\"id\":\"17\",\"created_at\":\"2016-02-28 00:28:34\",\"updated_at\":\"0000-00-00 00:00:00\",\"descripcion\":\"\",\"estado\":\"0\",\"imagen\":null,\"nombre\":\"Nieve\"},{\"id\":\"18\",\"created_at\":\"2016-02-28 00:28:47\",\"updated_at\":\"0000-00-00 00:00:00\",\"descripcion\":\"\",\"estado\":\"0\",\"imagen\":null,\"nombre\":\"Patinaje\"},{\"id\":\"19\",\"created_at\":\"2016-02-28 00:28:50\",\"updated_at\":\"0000-00-00 00:00:00\",\"descripcion\":\"\",\"estado\":\"0\",\"imagen\":null,\"nombre\":\"Pista\"},{\"id\":\"20\",\"created_at\":\"2016-02-28 00:28:53\",\"updated_at\":\"0000-00-00 00:00:00\",\"descripcion\":\"\",\"estado\":\"0\",\"imagen\":null,\"nombre\":\"Raqueta\"},{\"id\":\"21\",\"created_at\":\"2016-02-28 00:28:56\",\"updated_at\":\"0000-00-00 00:00:00\",\"descripcion\":\"\",\"estado\":\"0\",\"imagen\":null,\"nombre\":\"Resistencia\"},{\"id\":\"22\",\"created_at\":\"2016-02-28 00:28:59\",\"updated_at\":\"0000-00-00 00:00:00\",\"descripcion\":\"\",\"estado\":\"0\",\"imagen\":null,\"nombre\":\"Tabla\"},{\"id\":\"23\",\"created_at\":\"2016-02-28 00:29:04\",\"updated_at\":\"0000-00-00 00:00:00\",\"descripcion\":\"\",\"estado\":\"0\",\"imagen\":null,\"nombre\":\"Tiro al blanco\"},{\"id\":\"24\",\"created_at\":\"2016-02-28 00:29:07\",\"updated_at\":\"0000-00-00 00:00:00\",\"descripcion\":\"\",\"estado\":\"0\",\"imagen\":null,\"nombre\":\"Trineo\"},{\"id\":\"25\",\"created_at\":\"2016-02-28 00:29:13\",\"updated_at\":\"0000-00-00 00:00:00\",\"descripcion\":\"\",\"estado\":\"0\",\"imagen\":null,\"nombre\":\"Desplazamiento\"},{\"id\":\"26\",\"created_at\":\"2016-02-28 00:29:28\",\"updated_at\":\"0000-00-00 00:00:00\",\"descripcion\":\"\",\"estado\":\"0\",\"imagen\":null,\"nombre\":\"Deportes alternativos\"},{\"id\":\"27\",\"created_at\":\"2016-02-28 00:29:32\",\"updated_at\":\"0000-00-00 00:00:00\",\"descripcion\":\"\",\"estado\":\"0\",\"imagen\":null,\"nombre\":\"Aerobicos\"},{\"id\":\"28\",\"created_at\":\"2016-02-28 00:29:35\",\"updated_at\":\"0000-00-00 00:00:00\",\"descripcion\":\"\",\"estado\":\"0\",\"imagen\":null,\"nombre\":\"Danza\"},{\"id\":\"29\",\"created_at\":\"2016-02-28 00:29:40\",\"updated_at\":\"0000-00-00 00:00:00\",\"descripcion\":\"\",\"estado\":\"0\",\"imagen\":null,\"nombre\":\"Gimnasia\"},{\"id\":\"30\",\"created_at\":\"2016-02-28 00:30:07\",\"updated_at\":\"0000-00-00 00:00:00\",\"descripcion\":\"\",\"estado\":\"0\",\"imagen\":null,\"nombre\":\"Unidades deportivas\"}]";
    static String DEPORTES_JSON = "[{\"id\":\"24\",\"nombre\":\"Aguas bravas\",\"descripcion\":\"\",\"disciplina\":\"1\",\"imagen\":null},{\"id\":\"25\",\"nombre\":\"Aguas tranquilas\",\"descripcion\":\"\",\"disciplina\":\"1\",\"imagen\":null},{\"id\":\"26\",\"nombre\":\"Apnea\",\"descripcion\":\"\",\"disciplina\":\"1\",\"imagen\":null},{\"id\":\"27\",\"nombre\":\"Baloncesto acu\\u00e1tico\",\"descripcion\":\"\",\"disciplina\":\"1\",\"imagen\":null},{\"id\":\"28\",\"nombre\":\"Barrenar\",\"descripcion\":\"\",\"disciplina\":\"1\",\"imagen\":null},{\"id\":\"29\",\"nombre\":\"Bodyboard\",\"descripcion\":\"\",\"disciplina\":\"1\",\"imagen\":null},{\"id\":\"30\",\"nombre\":\"Bote drag\\u00f3n\",\"descripcion\":\"\",\"disciplina\":\"1\",\"imagen\":null},{\"id\":\"31\",\"nombre\":\"Buceo\",\"descripcion\":\"\",\"disciplina\":\"1\",\"imagen\":null},{\"id\":\"32\",\"nombre\":\"Carrera de lanchas motoras\",\"descripcion\":\"\",\"disciplina\":\"1\",\"imagen\":null},{\"id\":\"33\",\"nombre\":\"Descenso de r\\u00edos\",\"descripcion\":\"\",\"disciplina\":\"1\",\"imagen\":null},{\"id\":\"34\",\"nombre\":\"Hockey acu\\u00e1tico\",\"descripcion\":\"\",\"disciplina\":\"1\",\"imagen\":null},{\"id\":\"35\",\"nombre\":\"Kayak\",\"descripcion\":\"\",\"disciplina\":\"1\",\"imagen\":null},{\"id\":\"36\",\"nombre\":\"Kayak - polo\",\"descripcion\":\"\",\"disciplina\":\"1\",\"imagen\":null},{\"id\":\"37\",\"nombre\":\"Kitesurf\",\"descripcion\":\"\",\"disciplina\":\"1\",\"imagen\":null},{\"id\":\"38\",\"nombre\":\"Nado sincronizado\",\"descripcion\":\"\",\"disciplina\":\"1\",\"imagen\":null},{\"id\":\"39\",\"nombre\":\"Nataci\\u00f3n\",\"descripcion\":\"\",\"disciplina\":\"1\",\"imagen\":null},{\"id\":\"40\",\"nombre\":\"Pirag\\u00fcismo\",\"descripcion\":\"\",\"disciplina\":\"1\",\"imagen\":null},{\"id\":\"41\",\"nombre\":\"Remo\",\"descripcion\":\"\",\"disciplina\":\"1\",\"imagen\":null},{\"id\":\"42\",\"nombre\":\"Salto\",\"descripcion\":\"\",\"disciplina\":\"1\",\"imagen\":null},{\"id\":\"43\",\"nombre\":\"Skate acu\\u00e1tico\",\"descripcion\":\"\",\"disciplina\":\"1\",\"imagen\":null},{\"id\":\"44\",\"nombre\":\"Ski acu\\u00e1tico\",\"descripcion\":\"\",\"disciplina\":\"1\",\"imagen\":null},{\"id\":\"45\",\"nombre\":\"Skimboardino\",\"descripcion\":\"\",\"disciplina\":\"1\",\"imagen\":null},{\"id\":\"46\",\"nombre\":\"Surf\",\"descripcion\":\"\",\"disciplina\":\"1\",\"imagen\":null},{\"id\":\"47\",\"nombre\":\"Surf casting\",\"descripcion\":\"\",\"disciplina\":\"1\",\"imagen\":null},{\"id\":\"48\",\"nombre\":\"Trampolin\",\"descripcion\":\"\",\"disciplina\":\"1\",\"imagen\":null},{\"id\":\"49\",\"nombre\":\"Tubeo\",\"descripcion\":\"\",\"disciplina\":\"1\",\"imagen\":null},{\"id\":\"50\",\"nombre\":\"Vela\",\"descripcion\":\"\",\"disciplina\":\"1\",\"imagen\":null},{\"id\":\"51\",\"nombre\":\"Vela ligera\",\"descripcion\":\"\",\"disciplina\":\"0\",\"imagen\":null},{\"id\":\"52\",\"nombre\":\"Voleibol acu\\u00e1tico\",\"descripcion\":\"\",\"disciplina\":\"0\",\"imagen\":null},{\"id\":\"53\",\"nombre\":\"Wakeboard\",\"descripcion\":\"\",\"disciplina\":\"0\",\"imagen\":null},{\"id\":\"54\",\"nombre\":\"Waterpolo\",\"descripcion\":\"\",\"disciplina\":\"0\",\"imagen\":null},{\"id\":\"55\",\"nombre\":\"Windsurf\",\"descripcion\":\"\",\"disciplina\":\"0\",\"imagen\":null}]";

    static public void cargarDeportes (Context context) {

        try {
            JSONArray jsonArray = new JSONArray(DEPORTES_JSON);
            ConexionBaseDatosInsertar conexionBaseDatosInsertar = new ConexionBaseDatosInsertar(context);

            for (int x = 0; x < jsonArray.length(); x++) {

                //Se obtienen los datos y se mandan al deporte
                Deporte deporte = new Deporte(
                        Integer.parseInt(jsonArray.getJSONObject(x).getString("id")),
                        jsonArray.getJSONObject(x).getString("nombre"),
                        jsonArray.getJSONObject(x).getInt("disciplina"),
                        jsonArray.getJSONObject(x).getString("imagen")
                );

                conexionBaseDatosInsertar.insertarDeporte(deporte);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    static public void cargarDisciplinas (Context context) {

        try {
            JSONArray jsonArray = new JSONArray(DISCIPLINAS_JSON);
            ConexionBaseDatosInsertar conexionBaseDatosInsertar = new ConexionBaseDatosInsertar(context);

            for (int x = 0; x < jsonArray.length(); x++) {

                //Se obtienen los datos y se mandan a la medalla
                Disciplina disciplina = new Disciplina(
                        Integer.parseInt(jsonArray.getJSONObject(x).getString("id")),
                        jsonArray.getJSONObject(x).getString("nombre"),
                        jsonArray.getJSONObject(x).getString("descripcion"),
                        ""
                );
                conexionBaseDatosInsertar.insertarDisciplina(disciplina);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
