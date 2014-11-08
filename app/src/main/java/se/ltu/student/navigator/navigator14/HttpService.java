package se.ltu.student.navigator.navigator14;

import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

/**
 * Created by Navigator on 2014-05-06.
 */
public class HttpService extends AsyncTask<HttpRequestBase, String, String> {

    final HttpClient c;
    private HttpCallback caller;
    private String URL;
    private String filename = null;

    private String contentType;
    private boolean downloadToFile;

    public HttpService(String URL, HttpCallback call) {
        c = new DefaultHttpClient();
        this.URL = URL;
        this.caller = call;
    }

    public void setFileName(String n) {
        downloadToFile = true;
        filename = n;
    }

    public void POST(String data, String content) {
        HttpPost p = new HttpPost(URL);
        try {
            p.setHeader("Content-type", content);
            p.setEntity(new StringEntity(data, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            Log.d("HTTPService", "Unsupported encoding");
        }
        execute(p);
    }

    public void GET() {
        HttpGet g = new HttpGet(URL);
        execute(g);
    }

    public void GET(String params) {
        URL += params;
        GET();
    }

    private String run(HttpRequestBase req) throws IOException {
        Log.d("HTTPService", "Executing a " + req.getMethod() + " to " + req.getURI().toString());
        HttpResponse resp = c.execute(req);
        InputStream i = resp.getEntity().getContent();
        contentType = resp.getFirstHeader("content-type").getValue().split(";")[0];
        // content-type is usually something like "application/json; charset='UTF-8'". We don't need the charset.
        BufferedReader reader = new BufferedReader(new InputStreamReader(i, "UTF-8"), 8);
        String line;
        String result = "";
        while ((line = reader.readLine()) != null) result += line + "\n";
        return result;
    }

    @Override
    protected String doInBackground(HttpRequestBase... request) {
        try {
            return run(request[0]);
        } catch (IOException e) {
            this.cancel(false);
            return "";
        }
    }

    protected void onPostExecute(String result) {
        if (downloadToFile) {
            String name = filename + "." + contentType.split("/")[1];
            File f = new File(Environment.getExternalStorageDirectory() + "/Navigator/data", name);
            try {
                FileWriter w = new FileWriter(f);
                w.write(result);
                w.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            result = Environment.getExternalStorageDirectory() + "/Navigator/data/" + name;
        }
        caller.onHttpResponse(result);
    }
}
