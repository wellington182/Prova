package dominando.android.netfilmes.Model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Wellington on 02/10/2017.
 */

public class Genero implements Parcelable{
    private int id;
    private String descricao;

    public Genero(){}

    private Genero(Parcel from) {
        this.id = from.readInt();
        this.descricao = from.readString();
    }

    public static final Parcelable.Creator<Genero> CREATOR = new Parcelable.Creator<Genero>() {
        public Genero createFromParcel(Parcel in) {
            return new Genero(in);
        }

        @Override
        public Genero[] newArray(int size) {
            return new Genero[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return descricao;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(descricao);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Genero genero = (Genero) o;

        if (id != genero.id) return false;
        return descricao != null ? descricao.equals(genero.descricao) : genero.descricao == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (descricao != null ? descricao.hashCode() : 0);
        return result;
    }
}
