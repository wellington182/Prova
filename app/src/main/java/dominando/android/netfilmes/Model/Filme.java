package dominando.android.netfilmes.Model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Wellington on 02/10/2017.
 */

public class Filme implements Parcelable{
    private int id;
    private String titulo;
    private Genero genero;

    public Filme() {  }

    private Filme(Parcel from) {
        this.id = from.readInt();
        this.titulo = from.readString();
        this.genero = (Genero) from.readValue(Genero.class.getClassLoader());
    }

    public static final Parcelable.Creator<Filme> CREATOR = new Parcelable.Creator<Filme>() {
        public Filme createFromParcel(Parcel in) {
            return new Filme(in);
        }

        @Override
        public Filme[] newArray(int size) {
            return new Filme[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(titulo);
        dest.writeValue(genero);
    }
}
