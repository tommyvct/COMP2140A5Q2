class IntegerPFA
{
    int[] intArr;
    int size;

    /**
     * Constructor for {@code IntegerPFA} object.
     */
    public IntegerPFA()
    {
        this.intArr = new int[500];
        this.size = 0;
    }

    /**
     * Getter for {@code int}.
     *
     * @param index index of wanted {@code int}
     * @return wanted {@code int}
     */
    public int get(int index)
    {
        return this.intArr[index];
    }

    /**
     * Add a {@code int} to PFA, and double the array size when running out of space.
     *
     * @param toAdd {@code int} to add
     */
    public void add(int toAdd)
    {
        bigger();
        this.intArr[size++] = toAdd;
    }

    public void add(int toAdd, int index)
    {
        this.size++;
        bigger();

        for (int i = this.size; i > index; i--)
        {
            intArr[i] = intArr[i - 1];
        }

        this.intArr[index] = toAdd;
    }

    public boolean remove(int toRemove)
    {
        int index = linearSearch(toRemove);

        if (index < 0)
        {
            return false;
        }

        for (int i = index; i < this.size; i++)
        {
            intArr[i] = intArr[i + 1];
        }

        this.size--;
        return true;
    }

    public int linearSearch(int toFind)
    {
        for (int i = 0; i < this.size; i++)
        {
            if (intArr[i] == toFind)
            {
                return i;
            }
        }

        return -1;
    }

    private void bigger()
    {
        if (this.size >= intArr.length)
        {
            int[] temp = new int[2 * intArr.length];
            for (int i = 0; i < size; i++)
            {
                temp[i] = this.intArr[i];
            }
            this.intArr = temp;
        }
    }

    /**
     * Getter for PFA size.
     *
     * @return size of PFA.
     */
    public int size()
    {
        return this.size;
    }
}