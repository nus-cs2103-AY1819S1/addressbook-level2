import java.util.Comparator;
 class CompareName implements Comparator<Person>{
    @Override
    public int compare(Person p1, Person p2) {
        return p1.getName().toString()
                .compareToIgnoreCase(p2.getName().toString());
    }
}
