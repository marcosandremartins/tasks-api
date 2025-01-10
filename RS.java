import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class AppointmentSender {

    private static final String APPOINTMENT_URL = "http://google.com";
    private static final String CONTENT_TYPE = "application/json";

    public static void main(String[] args) throws IOException {
        LocalDateTime now = LocalDateTime.now();
        JSONObject newAppointmentPayload = new JSONObject();
        newAppointmentPayload.put("title", "appointment");
        newAppointmentPayload.put("description", "just a new appointment");
        newAppointmentPayload.put("completionDate", "2025-01-18T14:00:00");
        newAppointmentPayload.put("completed", false);

        sendAppointment(now, APPOINTMENT_URL, newAppointmentPayload);
    }

    private static void sendAppointment(LocalDateTime now, String url, JSONObject payload) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            HttpPost request = new HttpPost(url);
            request.setHeader("Content-Type", CONTENT_TYPE);
            request.setEntity(new StringEntity(payload.toString()));

            org.apache.http.HttpResponse response = httpClient.execute(request);
            int statusCode = response.getStatusLine().getStatusCode();

            String responseMessage = EntityUtils.toString(response.getEntity());

            if (statusCode != 200) {
                System.out.println(now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + " - Error: " + statusCode + " Message: " + responseMessage);
            } else {
                System.out.println(now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + " - Success: " + statusCode + " Message: " + responseMessage);
            }
        } finally {
            httpClient.close();
        }
    }
}

