package fileupload;
import java.util.Date;

public class MyFileDTO {

    private int idx;
    private String title;
    private String cate;
    private String ofile;
    private String sfile;
    private Date postdate;

    public int getIdx() {
        return idx;
    }

    public void setIdx(int idx) {
        this.idx = idx;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCate() {
        return cate;
    }

    public void setCate(String cate) {
        this.cate = cate;
    }

    public String getOfile() {
        return ofile;
    }

    public void setOfile(String ofile) {
        this.ofile = ofile;
    }

    public String getSfile() {
        return sfile;
    }

    public void setSfile(String sfile) {
        this.sfile = sfile;
    }

    public Date getPostdate() {
        return postdate;
    }

    public void setPostdate(Date postdate) {
        this.postdate = postdate;
    }

    @Override
    public String toString() {
        return "MyFileDTO{" +
                "idx=" + idx +
                ", title='" + title + '\'' +
                ", cate='" + cate + '\'' +
                ", ofile='" + ofile + '\'' +
                ", sfile='" + sfile + '\'' +
                ", postdate=" + postdate +
                '}';
    }
}
