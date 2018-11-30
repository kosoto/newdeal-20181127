package com.eomcs.util;

public class Queue<E> extends LinkedList<E> implements Cloneable {

  private int maxSize;
  
  public Queue() {
    maxSize = 100;
  }
  
  public Queue(int maxSize) {
    this.maxSize = maxSize;
  }
  
  @Override
  public Queue<E> clone() {
    Queue<E> temp = new Queue<>();
    for (int i = 0; i < size(); i++) {
      temp.add(get(i));
    }
    return (Queue<E>) temp;
  }
  
  public void offer(E value) {
    if (size() == maxSize)
      remove(0); // 꽉차면 맨 앞에 입력된 값을 제거한다.
    add(value);
  }
  
  public E poll() {
    //꺼낼 때는 poll
    if (size() > 0)
      return remove(0);
    return null;
  }
  
  public Iterator<E> iterator() {
    return new IteratorImpl<>();
    //중첩클래스를 바로 가져다 쓸 수 있다.
    // 받는 값이 없다.
  }
  
  
  //중첩클래스 사용
  class IteratorImpl<T> implements Iterator<T>{
    Queue<?> queue;
    //어떤 것이든 받겠다는 의미로 ?
    int count;
    {//인스턴스 블록
      this.queue=Queue.this.clone();
    }
    
    @Override
    public boolean hasNext() {
      return this.count < Queue.this.size();
    }

    @SuppressWarnings("unchecked")
    @Override
    public T next() {
      this.count++;
      return (T) queue.poll();
    }
    
  }
  
  /*
  public static void main(String[] args) throws Exception {
    Stack<String> stack = new Stack<>();
    stack.push("aaa");
    stack.push("bbb");
    stack.push("ccc");
    stack.push("ddd");
    stack.push("eee");
    stack.push("fff");
    
    Stack<String> temp1 = stack.clone();
    while (temp1.size() > 0) {
      System.out.println(temp1.pop());
    }
    System.out.println("----------------------");
    
    Stack<String> temp2 = stack.clone();
    while (temp2.size() > 0) {
      System.out.println(temp2.pop());
    }
  }*/
}
