
import java.util.Arrays;

/**
 * Problema03: Implemente un sistema de envío de mensajes a móviles. Existen dos
 * tipos de mensajes que se pueden enviar entre móviles, mensajes de texto (SMS)
 * y mensajes que contienen imágenes (MMS). Por un lado, los mensajes de texto
 * contienen un mensaje en caracteres que se desea enviar de un móvil a otro.
 * Por otro lado, los mensajes que contienen imágenes almacenan información
 * sobre la imagen a enviar, la cual se representará por el nombre del fichero
 * que la contiene. Independientemente del tipo de mensaje , cada mensaje tendrá
 * asociado un remitente de dicho mensaje y un destinatario. Ambos estarán
 * definidos obligatoriamente por un número de móvil, y opcionalmente se podrá
 * guardar información sobre su nombre. Además, los métodos enviarMensaje y
 * visualizarMensaje deben estar definidos.
 *
 * Para probar el diseño jerarquico de clases, instancia en el clase de prueba
 * Ejecutor, con datos ficticios.
 *
 * @author Matthew Castillo
 * @version 1.0
 */
class Mensaje {

    public String remitente;
    public String destinatario;
    public String numeroMovilRemitente;
    public String numeroMovilDestinatario;

    public Mensaje(String remitente, String destinatario,
            String numeroMovilRemitente, String numeroMovilDestinatario) {
        this.remitente = remitente;
        this.destinatario = destinatario;
        this.numeroMovilRemitente = numeroMovilRemitente;
        this.numeroMovilDestinatario = numeroMovilDestinatario;
    }

    public void guardarInformacion() {
        System.out.println("Remitente: " + remitente + " (" + numeroMovilRemitente + ")");
        System.out.println("Destinatario: " + destinatario + " (" + numeroMovilDestinatario + ")");
    }

    public void enviarMensaje() {
        System.out.println("Enviando mensaje de " + remitente + " a " + destinatario + "...");
    }

    public void visualizarMensaje() {
        System.out.println("De: " + remitente + " | Para: " + destinatario);
    }

    @Override
    public String toString() {
        return "Remitente: " + remitente + " (" + numeroMovilRemitente + ")"
                + "\nDestinatario: " + destinatario + " (" + numeroMovilDestinatario + ")";
    }
}

class Sms extends Mensaje {

    public String texto;

    public Sms(String remitente, String destinatario,
            String numeroMovilRemitente, String numeroMovilDestinatario,
            String texto) {
        super(remitente, destinatario, numeroMovilRemitente, numeroMovilDestinatario);
        this.texto = texto;
    }

    @Override
    public void enviarMensaje() {
        System.out.println("SMS enviado de " + remitente + " a " + destinatario);
        System.out.println("Mensaje: " + texto);
    }

    @Override
    public void visualizarMensaje() {
        System.out.println("--- SMS ---");
        System.out.println(toString());
    }

    @Override
    public String toString() {
        return super.toString() + "\nTexto: " + texto;
    }
}

class Mms extends Mensaje {

    public String imagen;

    public Mms(String remitente, String destinatario,
            String numeroMovilRemitente, String numeroMovilDestinatario,
            String imagen) {
        super(remitente, destinatario, numeroMovilRemitente, numeroMovilDestinatario);
        this.imagen = imagen;
    }

    @Override
    public void enviarMensaje() {
        System.out.println("MMS enviado de " + remitente + " a " + destinatario);
        System.out.println("Imagen: " + imagen);
    }

    @Override
    public void visualizarMensaje() {
        System.out.println("--- MMS ---");
        System.out.println(toString());
    }

    @Override
    public String toString() {
        return super.toString() + "\nImagen: " + imagen;
    }
}

public class Problema_3_EjecutorMensajesMoviles {

    public static void main(String[] args) {

        System.out.println("===== PRUEBA SMS =====");
        Sms sms = new Sms("Melany Abad", "Jeremy Pizarro",
                "0991234567", "0997654321",
                "Hola Jeremy, como estas?");
        sms.enviarMensaje();
        System.out.println();
        sms.visualizarMensaje();
        System.out.println();

        System.out.println("===== PRUEBA MMS =====");
        Mms mms = new Mms("Alberto Capurro", "Matthew Castillo",
                "0981234567", "0987654321",
                "foto_cumpleanios.png");
        mms.enviarMensaje();
        System.out.println();
        mms.visualizarMensaje();
        System.out.println();

        System.out.println("===== GUARDAR INFORMACION =====");
        sms.guardarInformacion();
        System.out.println();
        mms.guardarInformacion();
    }

}
/**
 * Run:
 * ===== PRUEBA SMS =====
    SMS enviado de Melany Abad a Jeremy Pizarro
    Mensaje: Hola Jeremy, como estas?

    --- SMS ---
    Remitente: Melany Abad (0991234567)
    Destinatario: Jeremy Pizarro (0997654321)
    Texto: Hola Jeremy, como estas?

    ===== PRUEBA MMS =====
    MMS enviado de Alberto Capurro a Matthew Castillo
    Imagen: foto_cumpleanios.png

    --- MMS ---
    Remitente: Alberto Capurro (0981234567)
    Destinatario: Matthew Castillo (0987654321)
    Imagen: foto_cumpleanios.png

    ===== GUARDAR INFORMACION =====
    Remitente: Melany Abad (0991234567)
    Destinatario: Jeremy Pizarro (0997654321)

    Remitente: Alberto Capurro (0981234567)
    Destinatario: Matthew Castillo (0987654321)
    BUILD SUCCESSFUL (total time: 0 seconds)
 */
