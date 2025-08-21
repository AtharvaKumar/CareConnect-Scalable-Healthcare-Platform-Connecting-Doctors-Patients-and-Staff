import React, { useEffect, useState } from 'react'
import axios from '../utils/axios'

export default function Billing(){
  const [list, setList] = useState([])
  const [form, setForm] = useState({ appointmentId:'', amount:0 })

  const load = async () => {
    const { data } = await axios.get('/billing/api/v1/billing')
    setList(data)
  }
  useEffect(()=>{ load() }, [])

  const add = async (e) => {
    e.preventDefault()
    await axios.post('/billing/api/v1/billing', { appointmentId: +form.appointmentId, amount: +form.amount })
    setForm({ appointmentId:'', amount:0 })
    load()
  }
  const pay = async (id) => {
    await axios.post(`/billing/api/v1/billing/${id}/pay`)
    load()
  }

  return (
    <div>
      <h2>Billing</h2>
      <form onSubmit={add} style={{display:'grid', gap:6, maxWidth:420}}>
        <input placeholder="Appointment ID" value={form.appointmentId} onChange={e=>setForm({...form, appointmentId:e.target.value})}/>
        <input placeholder="Amount" type="number" value={form.amount} onChange={e=>setForm({...form, amount:+e.target.value})}/>
        <button>Add</button>
      </form>
      <ul>
        {list.map(b=>(
          <li key={b.id}>
            Bill #{b.id} — Appt {b.appointmentId} — ₹{b.amount} — {b.status}
            {b.status !== 'PAID' && <button onClick={()=>pay(b.id)} style={{marginLeft:8}}>Pay</button>}
          </li>
        ))}
      </ul>
    </div>
  )
}
