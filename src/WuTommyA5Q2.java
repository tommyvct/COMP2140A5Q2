import java.io.BufferedReader;
import java.io.FileReader;

public class WuTommyA5Q2
{
    public static void main(String[] args)
    {
        Heap one = new Heap();
        Heap two = new Heap();

        try
        {
            BufferedReader br = new BufferedReader(new FileReader("A5Q2search1.txt"));
            String buffer = br.readLine();

            while (buffer != null)
            {
                String[] room = buffer.split(",");
                double price = Double.parseDouble(room[1]);
                double distance = Double.parseDouble(room[2]);

                one.insert(new HotelRoom(room[0], price, distance));

                buffer = br.readLine();
            }
        }
        catch (Exception e)
        {
            System.err.println(e);
        }

        try
        {
            BufferedReader br = new BufferedReader(new FileReader("A5Q2search2.txt"));
            String buffer = br.readLine();

            while (buffer != null)
            {
                String[] room = buffer.split(",");
                double price = Double.parseDouble(room[1]);
                double distance = Double.parseDouble(room[2]);

                two.insert(new HotelRoom(room[0], price, distance));

                buffer = br.readLine();
            }
        }
        catch (Exception e)
        {
            System.err.println(e);
        }

        Heap merge = new Heap(one, two);

        System.out.println("one");
        for (int i = 0; i < 5; i++)
        {
            System.out.print("Hotel Name: ");
            System.out.print(one.get(i).getName());
            System.out.print(", Price: ");
            System.out.print(one.get(i).getPrice());
            System.out.print(", Distance: ");
            System.out.println(one.get(i).getDistance());
        }

        System.out.println("two");
        for (int i = 0; i < 5; i++)
        {
            System.out.print("Hotel Name: ");
            System.out.print(two.get(i).getName());
            System.out.print(", Price: ");
            System.out.print(two.get(i).getPrice());
            System.out.print(", Distance: ");
            System.out.println(two.get(i).getDistance());
        }

        System.out.println("merge");
        for (int i = 0; i < 10; i++)
        {
            System.out.print("Hotel Name: ");
            System.out.print(merge.get(i).getName());
            System.out.print(", Price: ");
            System.out.print("" + merge.get(i).getPrice());
            System.out.print(", Distance: ");
            System.out.println("" + merge.get(i).getDistance());
        }
    }
}

class HotelRoom
{
    private String name;
    private double price;
    private double distance;

    public HotelRoom(String newName, double newPrice, double newDistance)
    {
        this.name = newName;
        this.price = newPrice;
        this.distance = newDistance;
    }


    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}


class HotelRoomArrayList
{
    private HotelRoom[] arr;
    private int size;

    /**
     * Constructor for {@code HotelRoomArrayList} object.
     */
    public HotelRoomArrayList()
    {
        this.arr = new HotelRoom[500];
        this.size = 0;
    }

    /**
     * Getter for {@code HotelRoom}.
     *
     * @param index index of wanted {@code HotelRoom}
     * @return wanted {@code HotelRoom}
     */
    public HotelRoom get(int index)
    {
        return this.arr[index];
    }

    /**
     * Add a {@code HotelRoom} to the end of PFA, and double the array size when running out of space.
     *
     * @param toAdd {@code HotelRoom} to add
     */
    public void add(HotelRoom toAdd)
    {
        bigger();
        this.arr[size++] = toAdd;
    }

    /**
     * Add a {@code HotelRoom} to PFA, and double the array size when running out of space.
     *
     * @param toAdd {@code HotelRoom} to add
     * @param index where to add
     */
    public void add(HotelRoom toAdd, int index)
    {
        this.size++;
        bigger();

        if (this.size - index >= 0) System.arraycopy(arr, index, arr, index + 1, this.size - index);
        this.arr[index] = toAdd;
    }

    public void set(HotelRoom toSet, int index)
    {
        if (index < 0 || index >= this.size)
        {
            System.err.println("AL::set(): boundary check failed.");
            return;
        }

        this.arr[index] = toSet;

    }

    /**
     * remove an item with given index from list.
     *
     * @param index index of item to remove
     */
    public HotelRoom remove(int index)
    {

        if (index < 0)
        {
            return null;
        }

        HotelRoom ret = this.arr[index];

        if (this.size - index >= 0) System.arraycopy(arr, index + 1, arr, index, this.size - index);

        this.size--;
        return ret;
    }

    /**
     * Find given item in this list using linear search.
     *
     * @param toFind Item to find
     * @return index of given item , or {@code -1} if nothing found.
     */
    public int linearSearch(HotelRoom toFind)
    {
        for (int i = 0; i < this.size; i++)
        {
            if (arr[i] == toFind)
            {
                return i;
            }
        }

        return -1;
    }

    /**
     * Swap 2 items at given indexes
     *
     * @param index1 swap this
     * @param index2 with this
     */
    public void swap(int index1, int index2)
    {
        if (index1 < 0 || index1 >= this.size || index2 < 0 || index2 >= this.size)
        {
            System.err.println("AL::Swap(): boundary check failed.");
            return;
        }

        HotelRoom temp = this.arr[index1];   // back up 1
        this.arr[index1] = this.arr[index2];  // put 2 in 1
        this.arr[index2] = temp;
    }

    /**
     * Copy item from given index to given index
     * @param fromIndex source index
     * @param toIndex destination index
     */
    public void copy(int fromIndex, int toIndex)
    {
        if (fromIndex < 0 || fromIndex >= this.size || toIndex < 0 || toIndex >= this.size)
        {
            System.err.println("AL::copy(): boundary check failed.");
            return;
        }

        this.arr[toIndex] = this.arr[fromIndex];
    }

    /**
     * Double the array at will.
     */
    private void bigger()
    {
        if (this.size >= arr.length)
        {
            HotelRoom[] temp = new HotelRoom[2 * arr.length];
            System.arraycopy(this.arr, 0, temp, 0, this.size);
            this.arr = temp;
        }
    }

    /**
     * Getter for size of this list.
     *
     * @return size of this list.
     */
    public int size()
    {
        return this.size;
    }
}

/**
 * Ascending Heap class.
 *
 * Why ascending: not hard to modify from descending version from book, easier to access
 */
@SuppressWarnings("CanBeFinal")
class Heap
{
    private HotelRoomArrayList list;

    /**
     * Constuct a empty heap.
     */
    public Heap()
    {
        list = new HotelRoomArrayList();
    }

    /**
     * Constrctor to merge 2 Heaps into 1. <p>
     * How it works:
     * <ul>
     *     <li>Construct an empty Heap of current instance</li>
     *     <li>Insert every item in heap 1 to current heap</li>
     *     <li>Same thing for heap 2</li>
     * </ul>
     *
     * @param merge1 first heap to merge
     * @param merge2 second heap to merge
     */
    public Heap(Heap merge1, Heap merge2)
    {
        this.list = new HotelRoomArrayList();

        int merge1Size = merge1.size();
        int merge2Size = merge2.size();

        for (int i = 0; i < merge1Size; i++)
        {
            this.insert(merge1.get(i));
        }

        for (int i = 0; i < merge2Size; i++)
        {
            this.insert(merge2.get(i));
        }
    }

    /**
     * Insert a {@code HotelRoom} in current Heap.
     *
     * @param newRoom {@code HotelRoom} to insert
     */
    public void insert(HotelRoom newRoom)
    {
        list.add(newRoom);
        trickleUp(list.size() - 1);
    }

    /**
     * Trickle the item at given index up
     * @param index item to trickle
     */
    private void trickleUp(int index)
    {
        int parent = (index - 1) / 2;
        HotelRoom bottom = list.get(index);

        while (index > 0 && list.get(parent).getPrice() > bottom.getPrice())     // ascending
//        while (index > 0 && list.get(parent).getPrice() < bottom.getPrice())      // descending
        {
            list.copy(parent, index);
            index = parent;
            parent = (parent - 1) / 2;
        }

        list.set(bottom, index);
    }

    /**
     * @return the size of heap.
     */
    public int size()
    {
        return this.list.size();
    }

    /**
     * Getter for {@code HotelRoom} at given index.
     * @param index to get
     * @return {@code HotelRoom} wanted, or {@code null} if invalid boundary.
     */
    public HotelRoom get(int index)
    {
        if (index < 0 || index >= this.list.size())
        {
            System.err.println("Heap::get(): boundary check failed.");
            return null;
        }

        return this.list.get(index);
    }
}