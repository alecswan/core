package com.inmethod.grid;

/** 
 * Interface that defines an Appendable Data Source
 * @author Tom B.
 */
public interface IAppendableDataSource<T> extends IDataSource<T>
{
  /** 
   * Function for appending an item to the end of the result data
   * @param index the index value to insert the new Item into
   * @param item Item to append to the end of the result data
   */
  public void insertRow(long index, T item);

  /** 
   * Function for deleting an item from the specified index of the result data
   * @param index the index value to delete the Item from
   * @param item Item to be deleted
   */
  public void deleteRow(long index, T item);
}
