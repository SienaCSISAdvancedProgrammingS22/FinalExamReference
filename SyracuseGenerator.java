import java.util.Arrays;
import java.util.Iterator;

/**
   Threaded program to find and report lengths of Syracuse sequences.

   @author Jim Teresco
   @version Spring 2022
*/

public class SyracuseGenerator implements Iterator<Long> {

    /** the next value to return, special value 0 means we are done */
    private long nextVal;

    /**
       Constructor for a SyracuseGenerator that begins with the
       given number.

       @param n the starting value
    */
    public SyracuseGenerator(long n) {

	if (n <= 0L) {
	    throw new IllegalArgumentException("must start with a positive number");
	}
	nextVal = n;
    }

    /**
       Return whether more values remain to be returned.

       @return whether more values remain to be returned
    */
    public boolean hasNext() {

	return nextVal >= 1L;
    }

    /**
       Return the next value in the sequence.

       @return the next value in the sequence
    */
    public Long next() {

	long retval = nextVal;
	if (nextVal == 1) nextVal = 0;
	else if (nextVal % 2 == 1) nextVal = 3 * nextVal + 1;
	else nextVal /= 2;
	return retval;
    }

    /**
       main method to test out the generator.

       @param args[0] the starting value for the sequence
       @param args[1] the number of threads to use
    */
    public static void main(String args[]) {

	if (args.length != 2) {
	    System.err.println("Usage: java SyracuseGenerator n numthreads");
	    System.exit(1);
	}
	int tryn = 0;
	int trynumthreads = 0;
	try {
	    tryn = Integer.parseInt(args[0]);
	    trynumthreads = Integer.parseInt(args[1]);
	}
	catch (NumberFormatException e) {
	    System.err.println("Invalid parameter(s)");
	    System.exit(1);
	}

	int n = tryn;
	int numthreads = trynumthreads;
	int lengths[] = new int[n+1];
	WorkerThread threads[] = new WorkerThread[numthreads];

	for (int t = 0; t < numthreads; t++) {
	    threads[t] = new WorkerThread(t) {

		    @Override
		    public void run() {
	    
			for (int toCompute = threadnum + 1; toCompute <= n;
			     toCompute += numthreads) {
			    Iterator<Long> gen = new SyracuseGenerator(toCompute);
			    while (gen.hasNext()) {
				gen.next();
				lengths[toCompute]++;
			    }
			}
		    }
		};
	    threads[t].start();
	}

	// working
	for (int t = 0; t < numthreads; t++) {
	    try {
		threads[t].join();
	    }
	    catch (InterruptedException e) {
		System.err.println(e);
	    }
	}

	// we have our answers
	System.out.println(Arrays.toString(lengths));
    }
}

class WorkerThread extends Thread {

    protected int threadnum;

    public WorkerThread(int threadnum) {
	// call superclass constructor
	super();
	this.threadnum = threadnum;
    }
}
