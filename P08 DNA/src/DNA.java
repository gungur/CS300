//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: DNA
// Course: CS 300 Spring 2022
//
// Author: Sai Gungurthi
// Email: sgungurthi@wisc.edu
// Lecturer: Hobbes LeGault
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name: (name of your pair programming partner)
// Partner Email: (email address of your programming partner)
// Partner Lecturer's Name: (name of your partner's lecturer)
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
// ___ Write-up states that pair programming is allowed for this assignment.
// ___ We have both read and understand the course Pair Programming Policy.
// ___ We have registered our team prior to the team registration deadline.
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons: NONE
// Online Sources: NONE
//
///////////////////////////////////////////////////////////////////////////////

/**
 * This class uses a linked queue to implement DNA transcription.
 */
public class DNA {

  // data fields

  // A two-dimensional array containing the mRNA codons in index 0 and the corresponding amino acid
  // (or STOP) in index 1. Used to translate the mRNA sequence to amino acids.
  protected static String[][] mRNAtoProteinMap =
      {{"UUU", "F"}, {"UUC", "F"}, {"UUA", "L"}, {"UUG", "L"}, {"UCU", "S"}, {"UCC", "S"},
          {"UCA", "S"}, {"UCG", "S"}, {"UAU", "Y"}, {"UAC", "Y"}, {"UAA", "STOP"}, {"UAG", "STOP"},
          {"UGU", "C"}, {"UGC", "C"}, {"UGA", "STOP"}, {"UGG", "W"}, {"CUU", "L"}, {"CUC", "L"},
          {"CUA", "L"}, {"CUG", "L"}, {"CCU", "P"}, {"CCC", "P"}, {"CCA", "P"}, {"CCG", "P"},
          {"CAU", "H"}, {"CAC", "H"}, {"CAA", "Q"}, {"CAG", "Q"}, {"CGU", "R"}, {"CGC", "R"},
          {"CGA", "R"}, {"CGG", "R"}, {"AUU", "I"}, {"AUC", "I"}, {"AUA", "I"}, {"AUG", "M"},
          {"ACU", "T"}, {"ACC", "T"}, {"ACA", "T"}, {"ACG", "T"}, {"AAU", "N"}, {"AAC", "N"},
          {"AAA", "K"}, {"AAG", "K"}, {"AGU", "S"}, {"AGC", "S"}, {"AGA", "R"}, {"AGG", "R"},
          {"GUU", "V"}, {"GUC", "V"}, {"GUA", "V"}, {"GUG", "V"}, {"GCU", "A"}, {"GCC", "A"},
          {"GCA", "A"}, {"GCG", "A"}, {"GAU", "D"}, {"GAC", "D"}, {"GAA", "E"}, {"GAG", "E"},
          {"GGU", "G"}, {"GGC", "G"}, {"GGA", "G"}, {"GGG", "G"}};
  // The queue containing the original DNA sequence.
  protected LinkedQueue<Character> DNA;

  /**
   * Creates the DNA queue from the provided String. Each Node contains a single Character from the
   * sequence.
   * 
   * @param sequence - a String containing the original DNA sequence
   */
  public DNA(String sequence) {
    this.DNA = new LinkedQueue<Character>();
    for (int i = 0; i < sequence.length(); i++) {
      this.DNA.enqueue(sequence.charAt(i)); // creates a node for each character in the sequence
    }
  }

  /**
   * Creates and returns a new queue of mRNA characters from the stored DNA. The transcription is
   * done one character at a time, as (A->U, T->A, C->G, G->C).
   * 
   * @return the queue containing the mRNA sequence corresponding to the stored DNA sequence
   */
  public LinkedQueue<Character> transcribeDNA() {
    LinkedQueue<Character> mRNA = new LinkedQueue<Character>();
    String stringDNA = "";
    while (!this.DNA.isEmpty()) {
      Character temp = DNA.dequeue(); // will re-enqueue at the end
      stringDNA = stringDNA + temp; // necessary for eventual re-enqueue
      // transcription from DNA to mRNA characters
      if (temp == 'A') {
        mRNA.enqueue('U');
      } else if (temp == 'T') {
        mRNA.enqueue('A');
      } else if (temp == 'G') {
        mRNA.enqueue('C');
      } else if (temp == 'C') {
        mRNA.enqueue('G');
      }
    }
    // re-enqueue DNA before returning mRNA
    for (int i = 0; i < stringDNA.length(); i++) {
      this.DNA.enqueue(stringDNA.charAt(i));
    }
    return mRNA;
  }

  /**
   * Creates and returns a new queue of amino acids from a provided queue of mRNA characters. The
   * translation is done three characters at a time, according to the static mRNAtoProteinMap
   * provided above. Translation ends either when you run out of mRNA characters OR when a STOP
   * codon is reached (i.e. the three-character sequence corresponds to the word STOP in the map,
   * rather than a single amino acid character).
   * 
   * @param mRNA - a queue containing the mRNA sequence corresponding to the stored DNA sequence
   * @return the queue containing the amino acid sequence corresponding to the provided mRNA
   *         sequence
   */
  public LinkedQueue<String> mRNATranslate(LinkedQueue<Character> mRNA) {
    LinkedQueue<String> aminoAcids = new LinkedQueue<String>(); // note it is String, not Character
    while (!mRNA.isEmpty()) {
      String codon = "";
      // takes into account cases when there are one or two characters left
      while (codon.length() < 3 && !mRNA.isEmpty()) {
        codon = codon + mRNA.dequeue();
      }
      // searches through the "map" for a codon match
      for (int i = 0; i < mRNAtoProteinMap.length; i++) {
        if (codon.equals(mRNAtoProteinMap[i][0])) {
          String temp = mRNAtoProteinMap[i][1]; // the corresponding amino acid
          if (temp.equals("STOP")) {
            return aminoAcids; // the STOP codon
          }
          aminoAcids.enqueue(temp);
        }
      }
    }

    return aminoAcids;
  }

  /**
   * A shortcut method that translates the stored DNA sequence to a queue of amino acids using the
   * other two methods in this class.
   * 
   * @return the queue containing the amino acid sequence corresponding to the stored DNA sequence,
   *         via its mRNA transcription
   */
  public LinkedQueue<String> translateDNA() {
    // could be done in fewer lines, but it is more readable this way
    LinkedQueue<Character> mRNA = this.transcribeDNA();
    LinkedQueue<String> aminoAcids = this.mRNATranslate(mRNA);
    return aminoAcids;
  }

}
