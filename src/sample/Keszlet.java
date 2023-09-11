package sample;

public class Keszlet {
    private int id;
    private String name;
    private String category;
    private String manufacturer;
    private int builtinmemory;
    private int year;
    private String memorytype;
    private int pieces;

    public Keszlet(int id,String name, String category, String manufacturer,
                   int builtinmemory, int year, String memorytype,int pieces) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.manufacturer = manufacturer;
        this.builtinmemory = builtinmemory;
        this.year = year;
        this.memorytype = memorytype;
        this.pieces = pieces;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public int getBuiltinmemory() { return builtinmemory; }

    public int getYear() {
        return year;
    }

    public String getMemorytype() {
        return memorytype;
    }

    public int getPieces() {
        return pieces;
    }
}
